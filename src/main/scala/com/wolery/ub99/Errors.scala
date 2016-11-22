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
//*
//*
//****************************************************************************

package com.wolery.ub99

//****************************************************************************

trait Errors
{
  def fail(message: String): Nothing =
  {
    throw new Exception(format(message))
  }

  def format(message: String): String

  def badLength()               = fail(s"the string beginning 'LEXEME...' is too long")
  def badString()               = fail(s"the string beginning 'LEXEME...' is missing a closing quote")
  def badComment()              = fail(s"the '/*' comment is not terminated with a matching '*/'")
  def badLexeme(s: String)      = fail(s"bad lexeme '$s'")
  def badChar(c: Char)          = fail(s"bad lexeme '$c'")
  def badByte(c: Char)          = fail(s"the file contains an illegal character '$c' (${c.toByte})")

  def badEndOfFile   ()         = fail(s"there is a syntax error at the end of the file")
  def badImplicitSlot()         = fail(s"the library is full; try storing '%0' in another library slot")
  def badExplicitSlot()         = fail(s"'LEXEME' is not a valid library slot; try a whole number between 1 and 99")
  def badEffectType  ()         = fail(s"'LEXEME' is not an effect; try %2")
  def badFieldName   (e:Effect) = fail(s"'LEXEME' is not a field of effect ${e.name}; try one of ${e.help}")
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

    fail(s"syntax error: wanted $w but got LEXEME")
  }

  def badLoadPath  (p: String) = fail(s"cannot open patch library '$p'")
  def badReadPath  (p: String) = fail(s"cannot open patch text file '$p'")
  def badDumpPath  (p: String) = fail(s"cannot dump patch text file to '$p'")
  def badSavePath  (p: String) = fail(s"cannot save patch library to '$p'")
  def badLoadFormat(p: String) = fail(s"cannot load '$p'; the file is not a patch library")
}

//****************************************************************************
