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

//****************************************************************************

case class Token (token: Char,lexeme: String,line: ℕ)

//****************************************************************************

final class Lexer (reader: Reader)
{
  def apply(): Token =
  {
    def onColon()                   = read() match
    {
      case '='                      ⇒ push('=');onToken('=')
      case  c                       ⇒ unread(c);onToken(':')
    }

    def onSign(s: Char)             = read() match
    {
      case '='                      ⇒ push(s);onToken('=')
      case c if c.isDigit           ⇒ push(s);onDigit(c)
      case c                        ⇒ badLexeme(s"$s$c")
    }

    def onSlash() =
    {
      def eol(): Unit               = read() match
      {
        case '\uFFFF' | '\n'        ⇒
        case  _                     ⇒ eol()
      }

      def eob(): Unit               = read() match
      {
        case '\uFFFF'               ⇒ badComment
        case '*'                    ⇒ eoc()
        case  _                     ⇒ eob()
      }

      def eoc(): Unit               = read() match
      {
        case '/'                    ⇒
        case  c                     ⇒ eob()
      }

      read() match
      {
        case '/'                    ⇒ eol();apply()
        case '*'                    ⇒ eob();apply()
        case  c                     ⇒ badLexeme(s"/$c")
      }
    }

    def onQuote() =
    {
      def eos(): Unit               = read() match
      {
        case '\uFFFF' | '\n'        ⇒ badString
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
      onToken('S')
    }

    def onUpper(c: Char) =
    {
      def more(): Unit              = read() match
      {
        case c if c.isLetterOrDigit ⇒ push(c);more()
        case c if "[].|->".contains(c)⇒push(c);more()
        case c                      ⇒ unread(c)
      }

      push(c)
      more()
      onToken('S')
    }

    def onDigit(c: Char) =
    {
      var t = 'ℤ'

      def eod(): Unit               = read() match
      {
        case c if c.isDigit         ⇒ push(c);         ;eod()
        case '.'                    ⇒ push('.');t = 'ℝ';eom()
        case 'e' | 'E'              ⇒ push('e');t = 'ℝ';eos()
        case c                      ⇒ unread(c);
      }

      def eom(): Unit               = read() match
      {
        case c if c.isDigit         ⇒ push(c);  eom()
        case 'e' | 'E'              ⇒ push('e');eos()
        case c                      ⇒ unread(c)
      }

      def eos(): Unit               = read() match
      {
        case '+'                    ⇒ push('+');eoe()
        case '-'                    ⇒ push('-');eoe()
        case c                      ⇒ unread(c);eoe()
      }

      def eoe(): Unit               = read() match
      {
        case c if c.isDigit         ⇒ push(c);eoe()
        case c                      ⇒ unread(c);
      }

      push(c)
      eod()
      onToken(t)
    }

    def onWhite()                   = apply()

    def onToken(token: Char): Token =
    {
      val l = m_buff.toString;

      m_buff.setLength(0)

      Token(token,l,m_line)
    }

//****************************************************************************

    def read(): Char            = m_read.read().toChar match
    {
      case '\n'                 ⇒ m_line += 1; '\n'
      case  c                   ⇒               c
    }

    def unread(c: Char)         = c match
    {
      case '\n'                 ⇒ m_line -= 1;m_read.unread(c)
      case  _                   ⇒             m_read.unread(c)
    }

    def lexeme: String          = m_buff.toString

    def push(c: Char) =
    {
      if (m_buff.length > longest_lexeme)
      {
        badLength
      }

      m_buff.append(c)
    }

//****************************************************************************

    def fail(message: String)   = throw new Error(s"PATH($m_line) : " + message)
    def badLength()             = fail(s"the string beginning '$lexeme...' is too long.")
    def badString()             = fail(s"the string beginning '$lexeme...' is missing a closing quote.")
    def badComment()            = fail(s"the '/*' comment is not terminated with a matching '*/'.")
    def badLexeme(s: String)    = fail(s"bad lexeme '$s'.")
    def badChar(c: Char)        = fail(s"bad lexeme '$c'.")
    def badByte(c: Char)        = fail(s"the file contains an illegal character '$c' (${c.toByte}).")

//****************************************************************************

    val c = read()

    c match
    {
      case '\uFFFF'             ⇒ onToken(c)
      case '(' | ',' | '#' | ')'⇒ onToken(c)
      case ':'                  ⇒ onColon( )
      case '/'                  ⇒ onSlash( )
      case '"'                  ⇒ onQuote( )
      case '+' | '-'            ⇒ onSign (c)
      case c if c.isDigit       ⇒ onDigit(c)
      case c if c.isUpper       ⇒ onUpper(c)
      case c if c.isWhitespace  ⇒ onWhite( )
      case c if 32>=c && c<=127 ⇒ badChar(c)
      case c                    ⇒ badByte(c)
    }
  }

  var m_line: ℕ                 = 1
  val m_buff: StringBuffer      = new StringBuffer(longest_lexeme)
  val m_read: PushbackReader    = new PushbackReader(reader)
}

//****************************************************************************
