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

import java.io._

import Utilities._

//****************************************************************************

final class Library extends Errors with Logging
{
  def format(message: String): String = message

  def load(path: String): Unit =
  {
    val io = new FileInputStream(path)
    val b = new Bytes(image_size)
    val n = io.read(b)

    if (n == 0)
    {
      badLoadPath(path)
    }

    if (n != b.length)
    {
      badLoadFormat(path)
    }

    if (substring(b,0,10).compareTo(image_magic) != 0)
    {
      badLoadFormat(path)
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

  def save(path: String): Unit =
  {
    val io = new FileOutputStream(path)
    val b = Array.fill[Byte](image_size)(0)

    image_magic.getBytes.copyToArray(b,0x0000)
    image_magic.getBytes.copyToArray(b,0x0040)

    for (i ← 0 until library_size)
    {
      val e = new Bytes(effect_size)

      m_slot(i).save(e)

      e.slice(16,16+name_size).copyToArray(b,name_table + i * name_size)
      e.copyToArray(b,header_size + i * effect_size)
    }

    val n = io.write(b)
  }

  def read(path: String): Unit =
  {
    Parser.parse(new FileReader(path),m_slot)
  }

  def dump(path: String): Unit =
  {
    val io = new FileWriter(path)
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

  val m_slot: Array[Effect] = Array.fill(library_size)(Effect())
}

//****************************************************************************
