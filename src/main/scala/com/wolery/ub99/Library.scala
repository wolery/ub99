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
  val image_magic  = "UB99 V1.00"                  // File format magic string
  val image_size   = 0x4400                        // Bytes per library file
  val effect_size  = 0x009F                        // Bytes per patch block
  val header_size  = 0x0600                        // Bytes per file header
  val name_table   = 0x0080                        // Bytes per name table

  val m_effect     = Array.fill(library_size)(Effect())

  lazy val image_prefix =
  {
    s"UB99 V1.00${"\u0000" * 54}UB99 V1.00${"\u0000" * 54}".getBytes
  }

  /**
   * @param io
   */
  def load(io: InputStream): Unit =
  {
    val b = new Bytes(image_size)
    val n = io.read(b)

    if (n!=b.length || !b.startsWith(image_prefix))
    {
      throw new IOException("the file is not a patch library")
    }

    var i = 0
    var o = header_size

    while (i != library_size)
    {
      m_effect(i) = Effect(b.slice(o,o + effect_size))
      o          += effect_size
      i          += 1
    }
  }

  /**
   * @param io
   */
  def save(io: OutputStream): Unit =
  {
    val b = new Bytes(image_size)

    image_prefix.copyToArray(b,0)

    for (i ← 0 until library_size)
    {
      val e = new Bytes(effect_size)

      m_effect(i).save(e)

      e.slice(16,16+name_size).copyToArray(b,name_table + i * name_size)
      e.copyToArray(b,header_size + i * effect_size)
    }

    val n = io.write(b)
  }

  /**
   * @param io
   */
  def read(io: Reader): Unit =
  {
    Parser.parse(io,m_effect)
  }

  /**
   * @param io
   */
  def dump(io: Writer): Unit  =
  {
    val m = Map[String,Slot]()
    val b = s"//${"*" * 76}\n"

    io.append(b).append('\n')

    for (s ← 0 until library_size if all_fields || !m_effect(s).isDefault)
    {
      val e = m_effect(s)
      val n = e.patchName
      val t = m.getOrElseUpdate(n,s)

      io.append(f"${s+1}%2d: ")

      if (s!=t && m_effect(s).kind==m_effect(t).kind)
      {
        m_effect(t).dump(io,t,m_effect(s))
        io.append(" // " + n)
      }
      else
      {
        m_effect(s).dump(io)
      }

      io.append('\n')
    }

    io.append('\n').append(b).flush()
  }
}

//****************************************************************************
