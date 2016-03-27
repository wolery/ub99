//**************************** Copyright © Jonathon Bell. All rights reserved.
//*
//*
//*  Version : $Header:$
//*
//*
//*  Purpose : Represents an open library of effect patches.
//*
//*
//*  Comments: This file uses a tab size of 2 spaces.
//*
//*
//****************************************************************************

package com.wolery.ub99

import java.io.InputStream
import java.io.OutputStream
import java.io.Writer

//****************************************************************************

final class Library
{
  def load(io: InputStream): Unit =
  {
    val b = new Array[Byte](image_size)
    val n = io.read(b)

    if (n == 0)
    {
      throw bad_load_path
    }

    if (n != b.length)
    {
      throw bad_load_format
    }

    if (new String(b.slice(0,10)).compareTo("UB99 V1.00") != 0)
    {
      throw bad_load_format
    }

    var i = 0
    var o = header_size

    while (i != library_size)
    {
      val x = b.slice(o,o+effect_size)
      m_slot(i) = Effect(b.slice(o,o+effect_size))
      o        += effect_size
      i        += 1
    }
  }

  def save(o: OutputStream): Unit  = {}

  /**
   *
   */
  def dump(io: Writer) =
  {
    io.append("//" + "*" * 76 + "\n\n")

    for (i ← 0 until library_size)
    {
      io.append(f"${i+1}%2d:   ")
      m_slot(i).dump(io)
      io.append('\n')
    }

    io.flush()
  }


  def read(i: InputStream): Unit   = {}

  def get(slot: Slot) : Effect     = ???
  def set(slot: Slot,e: Effect)    = ???

  val m_slot: Array[Effect]       = new Array(library_size)

  {
    for (slot ← 0 until library_size)
      m_slot(slot) = Effect("Amp")
  }

  val image_size  = 0x4400
  val image_magic = "UB99 V1.00"
  val header_size = 0x0600
  val effect_size = 0x009F
}

//****************************************************************************
/*
  val name_size         = 12          // ...number of bytes per patch name
  val library_size      = 99          // ...number of patches per library
  val image_size        = 0x4400      // ...number of bytes per library file
  val image_magic       = "UB99 V1.00"
  val effect_size       = 0x009F      // ...number of bytes per patch block
  val header_size       = 0x0600      // ...number of bytes per file header

*/
