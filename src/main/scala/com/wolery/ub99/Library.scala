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
import scala.collection.mutable.Map
import Utilities._

/**
 * @author Jonathon Bell
 */
object Library extends Logging
{
  val library_size = 99                            // Patches per library
  val image_size   = 0x4400                        // Bytes per library file
  val header_size  = 0x0600                        // Bytes per file header
  val name_table   = 0x0080                        // Offset of name table
  val effect_size  = 0x009F                        // Bytes per effect block
  val image_prefix = (s"UB99 V1.00${"\u0000" * 54}" * 2).getBytes

  val m_eff        = Array.fill(library_size)(Effect())

  /**
   * @param io
   */
  def load(io: InputStream): Unit =
  {
    val b = new Bytes(image_size)
    val n = io.read(b)
    var o = header_size

    if (n!=b.size || !b.startsWith(image_prefix))
    {
      throw new IOException("the file is not a patch library")
    }

    for (i ← 0 until library_size)
    {
      m_eff(i) = Effect(b.slice(o,o + effect_size))
      o       += effect_size
    }
  }

  /**
   * @param io
   */
  def save(io: OutputStream): Unit =
  {
    val b = new Bytes(image_size)
    var o = header_size
    var n = name_table

    image_prefix.copyToArray(b,0)

    for (i ← 0 until library_size)
    {
      val e = new Bytes(effect_size)
      m_eff(i).save(e)
      val s = e.slice(16,16 + name_size)

      e.copyToArray(b,o)
      s.copyToArray(b,n)

      o += effect_size
      n += name_size
    }

    io.write(b)
  }

  /**
   * @param io
   */
  def read(io: Reader): Unit =
  {
    Parser.parse(new org.anarres.cpp.CppReader(io),m_eff)
  }

  /**
   * @param io
   */
  def dump(io: Writer): Unit  =
  {
    val m = Map[String,Slot]()
    val b = s"//${"*" * 76}\n"

    io.append(b).append('\n')

    for (s ← 0 until library_size if all_fields || !m_eff(s).isDefault)
    {
      val e = m_eff(s)
      val n = e.patchName
      val t = m.getOrElseUpdate(n,s)

      io.append(f"${s+1}%2d: ")

      if (s!=t && m_eff(s).kind==m_eff(t).kind)
      {
        m_eff(t).dump(io,t,m_eff(s))
        io.append(" // " + n)
      }
      else
      {
        m_eff(s).dump(io)
      }

      io.append('\n')
    }

    io.append('\n').append(b).flush()
  }
}

//****************************************************************************
