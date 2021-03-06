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

package com.wolery

//****************************************************************************

package object ub99
{
  type Name             = String
  type Slot             = Int
  type Code             = Int
  type Kind             = Byte
  type Point            = (ℕ,ℝ)
  type Writer           = java.io.Writer
  type Bytes            = Array[Byte]
  type Logging          = util.Logging

  val none: Code        = 0x6B        // ...the value of an unassigned knob
  val name_size         = 12          // ...number of bytes per patch name
  val longest_lexeme    = 32          // ...maximum characters in a lexeme

  /**
   * Indicates whether or not to include every field value in an effect dump,
   * even those fields whose values have not changed from their default value.
   */
  var all_fields: Boolean = false                        // All field values?
}

//****************************************************************************
