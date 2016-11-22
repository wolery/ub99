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
    val b = new Bytes(image_size)
    val n = open(path,new FileInputStream(_),badLoadPath).read(b)

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
    val b = new Bytes(image_size)

    image_magic.getBytes.copyToArray(b,0x0000)
    image_magic.getBytes.copyToArray(b,0x0040)

    for (i ← 0 until library_size)
    {
      val e = new Bytes(effect_size)

      m_slot(i).save(e)

      e.slice(16,16+name_size).copyToArray(b,name_table + i * name_size)
      e.copyToArray(b,header_size + i * effect_size)
    }

    val n = open(path,new FileOutputStream(_),badSavePath).write(b)
  }

  def read(path: String): Unit =
  {
    Parser.parse(open(path,new FileReader(_),badReadPath),m_slot)
  }

  def dump(path: String): Unit =
  {
    val w = open(path,new FileWriter(_),badDumpPath)

    w.append(line_break).append('\n')

    for (i ← 0 until library_size if all_fields || !m_slot(i).equals(Effect.default))
    {
      w.append(f"${i+1}%2d: ")
      m_slot(i).dump(w)
      w.append('\n')
    }

    w.append('\n').append(line_break)
    w.flush()
  }

  def open[α](path: String,as: String ⇒ α,fail: String ⇒ Nothing): α =
  {
    try as(path) catch {case _: Exception ⇒ fail(path)}
  }

  val m_slot: Array[Effect] = Array.fill(library_size)(Effect())
}

//****************************************************************************
