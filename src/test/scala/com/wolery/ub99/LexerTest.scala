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

import java.io.FileReader
import java.io.StringReader

import org.scalatest.BeforeAndAfter
import org.scalatest.Finders
import org.scalatest.FunSuite
import org.scalatest.prop.TableDrivenPropertyChecks

//****************************************************************************

class LexerTest extends FunSuite with TableDrivenPropertyChecks
{
  def lex(lexeme: String): Token =
  {
    new Lexer(new StringReader(lexeme)).apply()
  }

  def stringLexer(string: String): Lexer =
  {
    new Lexer(new StringReader(string))
  }

  test("positive")
  {
    val good = Table(

      ( "Lexeme"     ,"Token"               ),

      ( "("          ,Token('(',"",      1) ),
      ( ","          ,Token(',',"",      1) ),
      ( ":"          ,Token(':',"",      1) ),
      ( "#"          ,Token('#',"",      1) ),
      ( ")"          ,Token(')',"",      1) ),

      ( ":="         ,Token('=',"=",     1) ),
      ( "+="         ,Token('=',"+",     1) ),
      ( "-="         ,Token('=',"-",     1) )
                             ,
      ( "ABCDEF"     ,Token('S',"ABCDEF",1) ),
      ( "Abcdef"     ,Token('S',"Abcdef",1) ),
      ( "A[c]ef"     ,Token('S',"A[c]ef",1) ),
      ( "A->D.6"     ,Token('S',"A->D.6",1) ),
      ( "A->D|6"     ,Token('S',"A->D|6",1) ),

      ( "\"ABCD\""   ,Token('S',"ABCD",  1) ),
      ( "\"Abcd\""   ,Token('S',"Abcd",  1) ),
      ( "\"A[c]\""   ,Token('S',"A[c]",  1) ),
      ( "\"A->D\""   ,Token('S',"A->D",  1) ),
      ( "\"A\" \"D\"",Token('S',"AD",    1) ),

      ( "\":=CD\""   ,Token('S',":=CD",  1) ),
      ( "\"+=CD\""   ,Token('S',"+=CD",  1) ),
      ( "\"AB#D\""   ,Token('S',"AB#D",  1) ),
      ( "\"A/*D\""   ,Token('S',"A/*D",  1) ),
      ( "\"A//D\""   ,Token('S',"A//D",  1) ),
      ( "\"A  D\""   ,Token('S',"A  D",  1) ),

      ( "012345"     ,Token('ℤ',"012345",1) ),
      ( "+12345"     ,Token('ℤ',"+12345",1) ),
      ( "-12345"     ,Token('ℤ',"-12345",1) ),

      ( "0.2345"     ,Token('ℝ',"0.2345",1) ),
      ( "0.2e+5"     ,Token('ℝ',"0.2e+5",1) ),
      ( "0.2e-5"     ,Token('ℝ',"0.2e-5",1) ),
      ( "0.2e-"      ,Token('ℝ',"0.2e-", 1) ),
      ( "0.e-"       ,Token('ℝ',"0.e-",  1) ))

    forAll(good)
    {
      (l,t) ⇒ assert(lex(l) == t)
    }
  }

  test("negative")
  {
    def fail(lexeme: String): String =
    {
      intercept[Error]{lex(lexeme)}.getMessage.substring(10)
    }

    val bad = Table(

      ( "Lexeme"      ,"Error Prefix"         ),

//    ( "/c"          ,"bad lexeme"           ),
//    ( "+c"          ,"bad lexeme"           ),
//    ( "/c"          ,"bad lexeme"           ),
//    ( "!c"          ,"bad char"             ),
      ( "\u009F"      ,"the file contains"     ),

      ( "/*"          ,"the '/*' comment"     ),
      ( "/*...."      ,"the '/*' comment"     ),
      ( "/*\uFFFF*/"  ,"the '/*' comment"     ),

      ( "\""          ,"the string beginning" ),
      ( "\"ABC"       ,"the string beginning" ),
      ( "\"ABC\nDEF"  ,"the string beginning" )
    )

    forAll(bad)
    {
      (l,e) ⇒ assert(fail(l).startsWith(e))
    }
  }

  test("lex read.txt")
  {
    val lex = new Lexer(new FileReader("src/test/resources/txt/read.txt"))
    var t   = lex()

    while (t.token != eof)
    {
      t.token match
      {
        case ')' => println(')')
        case  k  => print(k)
      }

      t = lex()
    }
  }
}

//****************************************************************************
