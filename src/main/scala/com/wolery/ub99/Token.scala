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

case class Token (token: Char,lexeme: String,line: ℕ,file: String = "") extends Errors
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

  def format(message: String): String =
  {
    s"$file($line) : ${message.replaceAll("LEXEME",lexeme)}"
  }
}

//****************************************************************************
