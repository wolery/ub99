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
  type ℕ                = Int
  type ℤ                = Int
  type ℝ                = Double
  type Name             = String
  type Slot             = Int
  type Code             = Int
  type Kind             = Byte
  type Point            = (ℕ,ℝ)
  type Writer           = java.io.Writer
  type Bytes            = Array[Byte]

  val none: Code        = 0x6B        // ...the value of an unassigned knob

  val name_size         = 12          // ...number of bytes per patch name
  val library_size      = 99          // ...number of patches per library
  val longest_lexeme    = 32          // ...maximum characters in a lexeme

  val image_magic       = "UB99 V1.00"// ...
  val image_size        = 0x4400      // ...number of bytes per library file
  val effect_size       = 0x009F      // ...number of bytes per patch block
  val header_size       = 0x0600      // ...number of bytes per file header
  val name_table        = 0x0080
}

//****************************************************************************
