//**************************** Copyright © Jonathon Bell. All rights reserved.
//*
//*
//*  Version : $Header:$
//*
//*
//*  Purpose : Parses the input stream for effects and adds them to a library.
//*
//*
//*  Comments: This file uses a tab size of 2 spaces.
//*
//*
//****************************************************************************

package com.wolery.ub99

import java.io.Reader
import java.io.StringReader

//****************************************************************************

final class Parser(reader: Reader)
{
  def apply() =
  {
    def parseStatement(): Unit = peek() match
    {
      case '#' ⇒ parseLine()
      case 'S' ⇒ parseEffect();
      case 'ℤ' ⇒ parseSlotOrCopy();
      case  _  ⇒ bad_syntax("#Sℤ",m_tok)
    }

    def parseSlotOrCopy(): Unit =
    {
      val z = read("ℤ")

      read(":(").token match
      {
        case ':' ⇒ onSlot(z)
        case '(' ⇒ onCopy(z);parseUpdates()
      }
    }

    def parseEffect(): Unit =
    {
      onCreate(read("S"))
      parseUpdates()
      onEffect()
    }

    def parseLine(): Unit =
    {
      val l = {skip("#");read("S")}

      if (l.value != "line")
      {
        bad_syntax("S",l)
      }

      onLine(read("ℤ"),read("S"))
    }

    def parseUpdates() =
    {
      skip("(")

      if (peek() != ')')
      {
        parseUpdate()

        while (peek() == ',')
        {
          skip(",")
          parseUpdate()
        }
      }

      skip(")")
    }

    def parseUpdate() =
    {
      val s = read("S")

      if (peek() != '=')
      {
        onUpdate(TokenOf('S',s.line,"NAME"),s)
      }
      else
      {
        read("=")
        onUpdate(s,read("S"))
      }
    }

    def peek(): Char =
    {
      if (m_tok == null)
      {
        m_tok = m_lex()
      }

      m_tok.token
    }

    def skip(want: String) =
    {
      assert(want.contains(m_tok.token))

      if (m_tok == null)
      {
        m_lex()
      }
      else
      {
        m_tok = null
      }
    }

    def read(want: String): Token =
    {
      def check(got: Token): Token =
      {
        if (want.contains(got.token))
          got
        else
          bad_syntax(want,got)
      }

      if (m_tok == null)
      {
        check(m_lex())
      }
      else
      {
        val t = m_tok
        m_tok = null
        check(t)
      }
    }

    def onLine(l: Token,f: Token)    = print("#")
    def onSlot(s: Token)             = print("S")
    def onCopy(t: Token)             = print("C")
    def onCreate(t: Token)           = print("T")
    def onUpdate(f: Token,v: Token)  = print("f = v")
    def onEffect()                   = print("E")

    def fail(message: String)      = throw new Error(s"PATH($m_lex.m_line) : " + message)
    def bad_syntax(w: String,g: Token)= fail(s"syntax error: wnated $w but got $g.")
    def bad_end_of_file  (line: ℕ) = fail(s"there is a syntax error at the end of the file.")
    def bad_explicit_slot(line: ℕ) = fail(s"'%0' is not a library slot; try a whole number between 1 and 99.")
    def bad_implicit_slot(line: ℕ) = fail(s"the library is full; try storing '%0' in another library slot.")
    def bad_effect_type  (line: ℕ) = fail(s"'%0' is not an effect; try %2.")
    def bad_field_name   (line: ℕ) = fail(s"'%0' is not a field of %2; try %3.")
    def bad_field_value  (line: ℕ) = fail(s"'%1' is not a legal value for %3.%0; try %2.")
    def bad_field_update (line: ℕ) = fail(s"the field '%0' can only be updated using the ':=' operator.")

    while (peek() != '∅')
    {
      parseStatement()
    }
  }

  type Token            = TokenOf[Any]

  var m_slt: ℤ          = 1
  var m_tok: Token      = null
  var m_eff: Effect     = null
  val m_lib: Library    = null
  val m_lex: Lexer      = new Lexer(reader)
}

//****************************************************************************

object Parser
{
  def apply(r: Reader) = new Parser(r)
  def apply(s: String) = new Parser(new StringReader(s))
}

//****************************************************************************
