//**************************** Copyright © Jonathon Bell. All rights reserved.
//*
//*
//*  Version : $Header:$
//*
//*
//*  Purpose : Unit tests for class Lexer.
//*
//*
//*  Comments: This file uses a tab size of 2 spaces.
//*
//*
//****************************************************************************

package com.wolery.ub99

import java.io.StringReader
import java.io.FileReader

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

//****************************************************************************

class LexerTest extends FunSuite with BeforeAndAfter
{
  def stringLexer(string: String): Lexer =
  {
    new Lexer(new StringReader(string))
  }

  def fileLexer(path: String): Lexer =
  {
    new Lexer(new FileReader(path))
  }

  test("lex read.txt")
  {
    val lex = fileLexer("src/test/resources/read.txt")
    var t   = lex()

    while (t.token != '∅')
    {
      t.token match
      {
        case ')' => println(')')
        case  k  => print(k)
      }
      t = lex()
    }
  }

  test("tokenize 1")  {tokenize("(,)",                  "(,)")       }
  test("tokenize 2")  {tokenize("1: Amp(JB1,TONE:=7.8)","ℤ:S(S,S=ℝ)")}

  test("bad string")
  {
    failWith("the string beginning")(
      "\"",
      "\"abc",
      "\"abc\ndef\"")
  }

  test("bad block comment")
  {
    failWith("the '/*' comment")(
      "/*",
      "/*abc",
      "/*abc*")
  }

  def failWith(prefix: String)(sources: String*) =
  {
    for (s ← sources)
    {
      val m = intercept[Error]
      {
        stringLexer(s)()
      }.getMessage.substring(10)

      assert(m.startsWith(prefix))
    }
  }

  def tokenize(source: String,tokens: String) =
  {
    val l = stringLexer(source)

    for (t ← tokens)
      assert(t == l().token)
  }
}

//****************************************************************************
