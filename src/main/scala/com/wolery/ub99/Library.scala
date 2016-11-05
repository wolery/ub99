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
import Utilities._

//****************************************************************************

final class Library
{
  def load(io: InputStream) =
  {
    val b = new Bytes(image_size)
    val n = io.read(b)

    if (n == 0)
    {
      bad_load_path()
    }

    if (n != b.length)
    {
      bad_load_format()
    }

    if (substring(b,0,10).compareTo("UB99 V1.00") != 0)
    {
      bad_load_format()
    }

    var i = 0
    var o = header_size

    while (i != library_size)
    {
      m_slot(i) = Effect(b.slice(o,o + effect_size))
      o        += effect_size
      i        += 1
    }
  }

  def save(io: OutputStream) =
  {
    val b = Array.fill[Byte](image_size)(0)

    "UB99 V1.00".getBytes.copyToArray(b,0x0000)
    "UB99 V1.00".getBytes.copyToArray(b,0x0040)

    for (i ← 0 until library_size)
    {
      val e = new Bytes(effect_size)

      m_slot(i).save(e)

      e.slice(16,16+name_size).copyToArray(b,name_table + i * name_size)
      e.copyToArray(b,header_size + i * effect_size)
    }

    val n = io.write(b)
  }

  def read(i: InputStream) = {}

  def dump(io: Writer) =
  {
    val break = "//" + "*" * 76 + '\n'

    io.append(break + '\n')

    for (i ← 0 until library_size)
    {
      io.append(f"${i+1}%2d:   ")
      m_slot(i).dump(io)
      io.append('\n')
    }

    io.append('\n' + break)
    io.flush()
  }

  def bad_load_path()     = error("cannot open patch library 'PATH'.")
  def bad_load_format()   = error("cannot load 'PATH'; the file is not a patch library.")
  def bad_edit_path()     = error("cannot open patch text file 'PATH'.")
  def bad_dump_path()     = error("cannot dump patch text file to 'PATH'.")
  def bad_save_path()     = error("cannot save patch library to 'PATH'.")

  def get(slot: Slot) : Effect     = ???
  def set(slot: Slot,e: Effect)    = ???

  val library_size      = 99          // ...number of patches per library
  val m_slot: Array[Effect]       = Array.fill[Effect](library_size)(Effect())

  val name_table  = 0x0080
  val image_size  = 0x4400
  val image_magic = "UB99 V1.00"
  val header_size = 0x0600
  val effect_size = 0x009F
}

//****************************************************************************
