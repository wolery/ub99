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

package com.wolery

//****************************************************************************

package object ub99
{
  type ℕ               = Int
  type ℤ               = Int
  type ℝ               = Double
  type Bool            = Boolean
  type Name            = String
  type Slot            = Int
  type Code            = Int
  type Kind            = Int
  type Index           = Int

  type Bytes           = Array[Byte]
  type Maybe[α]        = Option[α]

  type Point           = (ℕ,ℝ)
  type Points          = Seq[Point]

  val none:Code        = 0x6B         // ...the value of an unassigned knob

  val name_size         = 12          // ...number of bytes per patch name
  val library_size      = 99          // ...number of patches per library
  val image_size        = 0x4400      // ...number of bytes per library file
  val image_header      = "UB99 V1.00"
  val effect_size       = 0x009F      // ...number of bytes per patch block
  val header_size       = 0x0600      // ...number of bytes per file header
//val longest_lexeme    = MAX_PATH+2, // ...maximum characters in a lexeme

  object update extends Enumeration   // How to update an integral field?
  {
    val overwrite         = Value     // ...overwrite existing field value
    val increment         = Value     // ...increment existing field value
    val decrement         = Value     // ...decrement existing field value
    type update           = Value
  }

  def help_request        = new Error("\nTry %0.")
  def bad_load_path       = new Error("cannot open patch library 'PATH'.")
  def bad_load_format     = new Error("cannot load 'PATH'; the file is not a patch library.")
  def bad_edit_path       = new Error("cannot open patch text file 'PATH'.")
  def bad_dump_path       = new Error("cannot dump patch text file to 'PATH'.")
  def bad_save_path       = new Error("cannot save patch library to 'PATH'.")

  def bad_syntax(line: ℕ,text: String): Error =
  {
    new Error(s"PATH($line) : '$text' syntax error.")
  }

  def bad_end_of_file(line: ℕ): Error =
  {
    new Error(s"PATH($line) : there is a syntax error at the end of the file.")
  }

  def bad_source_byte(line: ℕ,byte: Byte): Error =
  {
    new Error(s"PATH($line) : the file contains an illegal character '${byte.toChar}' (${byte}).")
  }

  def bad_token_length(line: ℕ,text: String): Error =
  {
    new Error(s"PATH($line) : the string beginning '$text...' is too long.")
  }

  def bad_block_comment(line: ℕ): Error =
  {
    new Error("%PATH($line) : the '/*' comment is not terminated with a matching '*/'.")
  }

  def bad_quoted_string(line: ℕ) = new Error("PATH($line) : the string beginning '%1...' is missing a closing '\"'.")
  def bad_explicit_slot(line: ℕ) = new Error("PATH($line) : '%0' is not a library slot; try a whole number between 1 and 99.")
  def bad_implicit_slot(line: ℕ) = new Error("PATH($line) : the library is full; try storing '%0' in another library slot.")
  def bad_effect_type(line: ℕ)   = new Error("PATH($line) : '%0' is not an effect; try %2.")
  def bad_field_name(line: ℕ)    = new Error("PATH($line) : '%0' is not a field of %2; try %3.")
  def bad_field_value(line: ℕ)   = new Error("PATH($line) : '%1' is not a legal value for %3.%0; try %2.")
  def bad_field_update(line: ℕ)  = new Error("PATH($line) : the field '%0' can only be updated using the ':=' operator.")
}

//****************************************************************************
