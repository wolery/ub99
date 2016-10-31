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

  def put(bytes: Bytes,v: Int) =
  {
    assert(code != -16)
        println(this)

    if (code <= 0x0A)
    {
//    assert(between(v,0,1<<15))
      if (!between(v,0,0x7FFF))
      {
        println("sss")
      }
      assert(between(v,0,0x7FFF),"between(v,0,0x7FFF)")

      val o = 32 + 2 * code

      bytes(o + 0) = (v   >>   7).toByte
      bytes(o + 1) = (v & 0x007F).toByte
    }
    else
    {
//    assert(v < (1<<8))
      if (!between(v,0,0x007F))
      {
        println(this)
        println(name)
        println(code)
      }
      assert(between(v,0,0x007F),"between(v,0,0x007F)")

      val o    = 43 + code

      bytes(o) = (v & 0x007F).toByte
    }
  }

  def get(bytes: Bytes): ℤ =
  {
    assert(code != -16,"not name field")

    if (code <= 0x0A)
    {
      val o = 32 + 2 * code

      bytes(o)<<7 | bytes(o + 1)
    }
    else
    {
      val o = 43 + code

      bytes(o)
    }
  }
  override def toString: String = f"Field($name%-4s,$code,$m_val,$default)"
//def offset: ℕ = if (code <= 0x0A) 32 + 2*code else 43 +code
}

//****************************************************************************
