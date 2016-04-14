//**************************** Copyright © Jonathon Bell. All rights reserved.
//*
//*
//*  Version : $Header:$
//*
//*
//*  Purpose : Scans the input stream for tokens.
//*
//*
//*  Comments: This file uses a tab size of 2 spaces.
//*
//*
//****************************************************************************

package com.wolery.ub99

import java.io.Reader
import java.io.PushbackReader
import java.util.regex._;

//****************************************************************************

case class TokenOf[+Value] (token: Char,line: ℕ,value: Value = ())

//****************************************************************************

final class Lexer (reader: Reader)
{
  def apply(): TokenOf[Any] =
  {
    def onColon() = read() match
    {
      case '='                      ⇒           onToken('=')
      case  c                       ⇒ unread(c);onToken(':')
    }

    def onSign(c: Char) = read() match
    {
      case '='                      ⇒         onToken('=',c)
      case c if c.isDigit           ⇒ push(c);onDigit(c)
      case c                        ⇒ badSourceByte(c)
    }

    def onSlash() =
    {
      def eol(): Unit               = read() match
      {
        case 0xFFFF                 ⇒
        case '\n'                   ⇒ m_line += 1;
        case  _                     ⇒ eol()
      }

      def eob(): Unit               = read() match
      {
        case 0xFFFF                 ⇒ badBlockComment()
        case '\n'                   ⇒ m_line += 1;eob()
        case '*'                    ⇒             eoc()
        case  _                     ⇒             eob()
      }

      def eoc(): Unit              = read() match
      {
        case '/'                    ⇒
        case '\n'                   ⇒ m_line += 1;eob()
        case  c                     ⇒ unread(c);  eob()
      }

      read() match
      {
        case '/'                    ⇒ eol();apply()
        case '*'                    ⇒ eob();apply()
        case  c                     ⇒ badSourceByte(c)
      }
    }

    def onQuote() =
    {
      def eos(): Unit               = read() match
      {
        case 0xFFFF | '\n'          ⇒ badString(pop())
        case '"'                    ⇒ space()
        case c                      ⇒ push(c);eos()
      }

      def space(): Unit             = read() match
      {
        case ' '                    ⇒ quote()
        case  c                     ⇒ unread(c)
      }

      def quote(): Unit             = read() match
      {
        case '"'                    ⇒ eos()
        case  c                     ⇒ unread(c)
      }

      eos()
      TokenOf('S',m_line,pop())
    }

    def onUpper(c: Char) =
    {
      def more(): Unit = read() match
      {
        case c if c.isLetterOrDigit ⇒ push(c); more()
        case c if "[].|>".contains(c)⇒ push(c); more()
        case c                      ⇒ unread(c)
      }

      push(c)
      more()
      TokenOf('S',m_line,pop())
    }

    def onDigit(c: Char) =
    {
      var int = true

      def eod(): Unit               = read() match
      {
        case c if c.isDigit         ⇒ push(c);eod()
        case '.'                    ⇒ push(c);int = false;eom()
        case 'e' | 'E'              ⇒ push(c);int = false;eos()
        case c                      ⇒ unread(c);
      }

      def eom(): Unit               = read() match
      {
        case c if c.isDigit         ⇒ push(c);eom()
        case c                      ⇒ unread(c)
      }

      def eos(): Unit               = read() match
      {
        case '+' | '-'              ⇒ push(c);  eoe()
        case c                      ⇒ unread(c);eoe()
      }

      def eoe(): Unit               = read() match
      {
        case c if c.isDigit         ⇒ push(c);eoe()
        case c                      ⇒ unread(c);
      }

      push(c)
      eod()

      if (int)
        TokenOf('ℤ',m_line,pop().toInt)
      else
        TokenOf('ℝ',m_line,pop().toDouble)
    }

    def onWhite(c: Char) = c match
    {
      case '\n'                     ⇒ m_line += 1;apply()
      case  _                       ⇒             apply()
    }

    def onToken[Value](token: Char,value : Value = ()) =
    {
      TokenOf(token,m_line)
    }

    def push(c: Char) =
    {
      if (m_buff.length > longest_lexeme)
      {
        badLexLength(pop())
      }

      m_buff.append(c)
    }

    def pop()                   = {val s=m_buff.toString;m_buff.setLength(0);s}
    def read()                  = m_read.read().toChar
    def unread(c: Char)         = m_read.unread(c)

    def fail(message: String)   = throw new Error(s"PATH($m_line) : " + message)
    def badString(s: String)    = fail(s"the string beginning '$s...' is missing a closing quote.")
    def badLexLength(s: String) = fail(s"the string beginning '$s...' is too long.")
    def badBlockComment()       = fail(s"the '/*' comment is not terminated with a matching '*/'.")
    def badSourceByte(c: Char)  = fail(s"the file contains an illegal character '$c' (${c.toByte}).")

    val c = read()

    c match
    {
      case 0xFFFF               ⇒ onToken('∅')
      case '(' | ',' | '#' | ')'⇒ onToken(c)
      case ':'                  ⇒ onColon( )
      case '/'                  ⇒ onSlash( )
      case '"'                  ⇒ onQuote( )
      case '+' | '-'            ⇒ onSign (c)
      case c if c.isDigit       ⇒ onDigit(c)
      case c if c.isUpper       ⇒ onUpper(c)
      case c if c.isWhitespace  ⇒ onWhite(c)
      case c                    ⇒ badSourceByte(c)
    }
  }

  var m_line: ℕ                 = 1
  val m_buff: StringBuffer      = new StringBuffer(32)
  val m_read: PushbackReader    = new PushbackReader(reader)
}

//****************************************************************************
