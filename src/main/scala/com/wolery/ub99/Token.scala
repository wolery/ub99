//**************************** Copyright © Jonathon Bell. All rights reserved.
//*
//*
//*  Version : $Header:$
//*
//*
//*  Purpose :
//*
//*
//*  Comments: This file uses a tab size of 2 spaces.
//*                                                                     0-0
//*                                                                   (| v |)
//**********************************************************************w*w***

package com.wolery.ub99

//****************************************************************************

case class Token (token: Char,lexeme: String,line: ℕ,file: String = "")
{
  def is(tokens: String): Boolean = tokens.contains(token)

  def real: ℝ                   = {assert(is("ℤℝ"));lexeme.toDouble}
  def integer: ℤ                = {assert(is("ℤ")); lexeme.toInt}
  def name: Name                = {assert(is("S")); lexeme.intern}
  def string: String            = {assert(is("S")); lexeme}

  def copy(token: Char,lexeme: String): Token =
  {
    Token(token,lexeme,line,file)
  }

  def fail(message: String): Nothing =
  {
    throw new Exception(s"$file($line): $message")
  }

  def badLength()               = fail(s"the string beginning '$lexeme...' is too long")
  def badString()               = fail(s"the string beginning '$lexeme...' is missing a closing quote")
  def badComment()              = fail(s"the '/*' comment is not terminated with a matching '*/'")
  def badLexeme(s: String)      = fail(s"bad lexeme '$s'")
  def badChar(c: Char)          = fail(s"bad lexeme '$c'")
  def badByte(c: Char)          = fail(s"the file contains an illegal character '$c' (${c.toByte})")

  def badEndOfFile   ()         = fail(s"there is a syntax error at the end of the file")
  def badImplicitSlot()         = fail(s"the library is full; try storing '%0' in another library slot")
  def badExplicitSlot()         = fail(s"'$lexeme' is not a valid library slot; try a whole number between 1 and 99")
  def badEffectType  ()         = fail(s"'$lexeme' is not an effect; try %2")
  def badFieldName   (e:Effect) = fail(s"'$lexeme' is not a field of effect ${e.name}; try one of ${e.help}")
  def badFieldValue  ()         = fail(s"'%1' is not a legal value for %3.%0; try %2")
  def badFieldUpdate ()         = fail(s"the field '%0' can only be updated using the ':=' operator")

  def badSyntax(wanted: String) =
  {
    val w = wanted match
    {
      case "S"   ⇒ "a name"
      case "ℤℝ"  ⇒ "a number"
      case "Sℤℝ" ⇒ "a name or number"
      case _     ⇒ wanted
    }

    fail(s"syntax error: wanted ${describe(wanted)} but got $lexeme")
  }

  def describe(tokens: String): String = tokens match
  {
    case "S"   ⇒ "a name"
    case "ℤℝ"  ⇒ "a number"
    case "Sℤℝ" ⇒ "a name or number"
    case _     ⇒ tokens
  }
}

//****************************************************************************
