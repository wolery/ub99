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
  val longest_lexeme    = 32          // ...maximum characters in a lexeme
  val eof               = '\uFFFF'

  object update extends Enumeration   // How to update an integral field?
  {
    val overwrite         = Value     // ...overwrite existing field value
    val increment         = Value     // ...increment existing field value
    val decrement         = Value     // ...decrement existing field value
    type update           = Value
  }

  def error(msg: String): Nothing = throw new Error(msg)
  def bad_load_path()     = error("cannot open patch library 'PATH'.")
  def bad_load_format()   = error("cannot load 'PATH'; the file is not a patch library.")
  def bad_edit_path()     = error("cannot open patch text file 'PATH'.")
  def bad_dump_path()     = error("cannot dump patch text file to 'PATH'.")
  def bad_save_path()     = error("cannot save patch library to 'PATH'.")
}

//****************************************************************************
