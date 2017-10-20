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
//*                                                                     0-0
//*                                                                   (| v |)
//**********************************************************************w*w***

package com.wolery.ub99

import java.io.Reader
import Utilities._

//****************************************************************************

object Parser extends Logging
{
  def parse(reader: Reader,effects: Array[Effect]): Unit =
  {
    val m_lex: Lexer  = new Lexer(reader)
    var m_tok: Token  = null
    var m_eff: Effect = null
    var m_slt: ℕ      = 1

    def parseStatement(): Unit = peek() match
    {
      case '#' ⇒ parseLine()
      case 'S' ⇒ parseEffect()
      case 'ℤ' ⇒ parseSlotOrCopy()
      case  _  ⇒ m_tok.badSyntax("#Sℤ")
    }

    def parseLine(): Unit =
    {
      val l = {skip("#");read("S")}

      if (l.lexeme != "line")
      {
        l.badSyntax("line")
      }

      onLine(read("ℤ"),read("S"))
    }

    def parseEffect(): Unit =
    {
      onCreate(read("S"))
      parseUpdates()
      onEffect()
    }

    def parseSlotOrCopy(): Unit =
    {
      val i = read("ℤ")

      peek() match
      {
        case ':' ⇒ skip(":");onSlot(i)
        case '(' ⇒ onCopy(i);parseUpdates();onEffect()
      }
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

      if (":+-".contains(peek()))
      {
        onUpdate(s,read(":+-"),read("ℤℝS"))
      }
      else
      {
        onUpdate(s.copy('S',"NAME"),s.copy(':',":="),s)
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
      if (m_tok == null)
      {
        val t = m_lex()
        assert(want.contains(t.token),s"want $want but got $t")
        t
      }
      else
      {
        assert(want.contains(m_tok.token))
        m_tok = null
      }
    }

    def read(want: String): Token =
    {
      if (m_tok == null)
      {
        m_tok = m_lex()
      }

      if (!want.contains(m_tok.token))
      {
        m_tok.badSyntax(want)
      }

      val t = m_tok
      m_tok = null
      t
    }

////

    def onLine(line: Token,file: Token): Unit =
    {
      m_lex.setLine(line.integer,file.string)

      log.debug(s"#line ${line.lexeme} '${file.lexeme}'\n")
    }

    def onSlot(slot: Token): Unit =
    {
      m_slt = slot.integer

      if (outside(m_slt,1,99))
      {
        m_tok.badExplicitSlot()
      }

      log.debug(s"${slot.lexeme}: ")
    }

    def onCopy(slot: Token): Unit =
    {
      val s = slot.integer

      if (Utilities.outside(s,1,99))
      {
        m_tok.badExplicitSlot()
      }

      val e: Effect = effects(s).copy

      log.debug(s"${slot.lexeme}(")
    }

    def onCreate(kind: Token): Unit =
    {
      log.debug(s"${kind.lexeme}(")
      m_eff = Effect(kind.name)

      log.debug(s"${kind.lexeme}(")
    }

    def onUpdate(field: Token,update: Token,value:Token): Unit =
    {
      try
      {
        val f = m_eff(field.name)

        (update.token,value.token) match
        {
          case(':','S') ⇒ foo(f.overwrite(value.name))
          case('+','S') ⇒ m_tok.badSyntax("ℤℝ")
          case('-','S') ⇒ m_tok.badSyntax("ℤℝ")

          case(':', _ ) ⇒ foo(f.overwrite(value.real))
          case('+', _ ) ⇒ foo(f.increment(value.real))
          case('-', _ ) ⇒ foo(f.decrement(value.real))

          case( _ , _ ) ⇒ m_tok.badSyntax("ℤℝ")
        }

        def foo(action: ⇒ Boolean) =
        {
          if (!action)
          {
            println("bad field value")
          }
        }
      }
      catch
      {
        case _: Exception ⇒ field.badFieldName(m_eff)
      }

      log.debug(f"${field.lexeme}%-4s${update.lexeme}${value.lexeme},")
    }

    def onEffect(): Unit =
    {
      if (outside(m_slt,1,99))
      {
        m_tok.badImplicitSlot()
      }

      effects(m_slt - 1) = m_eff
      m_slt += 1
      log.debug(s")\n")
    }

    while (peek() != '∅')
    {
      parseStatement()
    }
  }
}

//****************************************************************************
