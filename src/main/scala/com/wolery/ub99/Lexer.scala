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
//*                                                                     0-0
//*                                                                   (| v |)
//**********************************************************************w*w***

package com.wolery
package ub99

import java.io.{PushbackReader,Reader}

//****************************************************************************

final class Lexer (reader: Reader) extends Logging
{
  def apply(): Token =
  {
    def onColon()                   = read() match
    {
      case '='                      ⇒ push(':','=');onToken(':')
      case  c                       ⇒ unread(c)    ;onToken(':')
    }

    def onSign(s: Char)             = read() match
    {
      case '='                      ⇒ push(s,'=');onToken(s)
      case c if c.isDigit           ⇒ push(s)    ;onDigit(c)
      case c                        ⇒ onError(c).badLexeme(s"$s$c")
    }

    def onSlash() =
    {
      def eol(): Unit               = read() match
      {
        case '∅' | '\n'             ⇒
        case  _                     ⇒ eol()
      }

      def eob(): Unit               = read() match
      {
        case '∅'                    ⇒ onError('∅').badComment()
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
        case  c                     ⇒ onError(c).badLexeme(s"/$c")
      }
    }

    def onQuote() =
    {
      def eos(): Unit               = read() match
      {
        case '\n' | '∅'             ⇒ onError('∅').badString()
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

    def onAlpha(c: Char) =
    {
      def more(): Unit              = read() match
      {
        case c if c.isLetterOrDigit ⇒ push(c);more()
        case c if "[+->.]".contains(c)⇒push(c);more()
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
        case c if c.isDigit         ⇒ push(c)  ;       ;eod()
        case '.'                    ⇒ push('.');t = 'ℝ';eom()
        case 'e' | 'E'              ⇒ push('e');t = 'ℝ';eos()
        case c                      ⇒ unread(c);
      }

      def eom(): Unit               = read() match
      {
        case c if c.isDigit         ⇒ push(c)  ;eom()
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
      val s = m_buff.toString;

      m_buff.setLength(0)

      Token(token,s,m_line,m_file)
    }

    def onError(c: Char): Token =
    {
      Token('?',c.toString,m_line,m_file)
    }

//****************************************************************************

    def read(): Char            = m_read.read().toChar match
    {
      case '\n'                 ⇒ m_line += 1; '\n'
      case '\uFFFF'             ⇒              '∅'
      case  c                   ⇒               c
    }

    def unread(c: Char) =
    {
      if (c == '\n')
      {
        m_line -= 1
      }

      m_read.unread(c)
    }

    def push(characters: Char*) =
    {
      if (m_buff.length + characters.size > longest_lexeme)
      {
        onError('?').badLength()
      }

      characters.foreach{m_buff.append(_)}
    }

//****************************************************************************

    val c = read()

    c match
    {
      case '('|','|'#'|')'|'∅'  ⇒ onToken(c)
      case ':'                  ⇒ onColon( )
      case '/'                  ⇒ onSlash( )
      case '"'                  ⇒ onQuote( )
      case '+' | '-'            ⇒ onSign (c)
      case c if c.isDigit       ⇒ onDigit(c)
      case c if c.isLetter      ⇒ onAlpha(c)
      case c if c.isWhitespace  ⇒ onWhite( )
      case c if 32>=c && c<=127 ⇒ onError(c).badChar(c)
      case c                    ⇒ onError(c).badByte(c)
    }
  }

  def setLine(line: ℕ,file: String) =
  {
    require(line>0 && file!=null)
    m_line = line
    m_file = file
  }

  var m_line: ℕ                 = 1
  var m_file: String            = "stdin"
  val m_buff: StringBuffer      = new StringBuffer(longest_lexeme)
  val m_read: PushbackReader    = new PushbackReader(reader)
}

//****************************************************************************
