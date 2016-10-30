//**************************** Copyright © Jonathon Bell. All rights reserved.
//*
//*
//*  Version : $Header:$
//*
//*
//*  Purpose : Partially implements the abstract Field interface.
//*
//*
//*  Comments: This file uses a tab size of 2 spaces.
//*
//*
//****************************************************************************

package com.wolery.ub99

import Utilities.between

/**
 * Partially implements the abstract Field interface.
 *
 * @tparam Value    -
 * @param  name      -
 * @param  code      -
 * @param  default  -
 *
 * @author Jonathon Bell
 */
abstract class FieldOf[Value]
(
  val name:    Name,
  val code:    Code,
  val default: Value
)
extends Field
{
  require(between(name.length,1,4))
  require(between(code,-16,none))

  var m_val: Value                  = default

  def dirty: Boolean                = m_val != default

  def set(real  : ℝ)                = {}
  def set(name  : Name)             = {}
  def set(effect: Effect)           = {}

  def load(bytes:  Bytes)           = {}

  def dump(writer: Writer)          = writer.append(s"$name:=$m_val")

  def save(bytes: Bytes) =
  {
    put(bytes,toInt)
  }

  def put(bytes: Bytes,v: Short) =
  {
    assert(code != -16)

    if (code <= 0x0A)
    {
//    assert(between(v,0,1<<15))
      assert(between(v,0,0x7FFF))

      val o = 32 + 2 * code

      bytes(o + 0) = (v   >>   7).toByte
      bytes(o + 1) = (v & 0x007F).toByte
    }
    else
    {
//    assert(v < (1<<8))
      assert(between(v,0,0x007F))

      val o    = 43 + code

      bytes(o) = (v & 0x007F).toByte
    }
  }

  def get(bytes: Bytes): ℤ =
  {
    assert(code != -16)

    if (code <= 0x0A)                                    // Occupies 2 chars?
    {
      val o = 32 + 2 * code                              // ...compute offset

      return bytes(o)<<7 | bytes(o + 1)                  // ...read, combine
    }
    else                                                 // No, one is enough
    {
      val o = 43 + code

      return bytes(o)                                    // ...read the byte
    }
  }

  def offset: ℕ = if (code <= 0x0A) 32 + 2*code else 43 +code
}

//****************************************************************************
