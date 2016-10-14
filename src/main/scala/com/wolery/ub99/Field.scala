//**************************** Copyright © Jonathon Bell. All rights reserved.
//*
//*
//*  Version : $Header:$
//*
//*
//*  Purpose : Represents a field within an effect.
//*
//*
//*  Comments: This file uses a tab size of 2 spaces.
//*
//*
//****************************************************************************

package com.wolery.ub99

import java.io.Writer

//****************************************************************************

trait Field
{
  def name                           : Name
  def code                           : Code

  def dirty                          : Bool

  def load(bytes:  Bytes)            : Unit
  def save(bytes:  Bytes)            : Unit
  def dump(writer: Writer)           : Unit

  def copy                           : Field

  def set(v: ℝ)                      : Unit
  def set(v: Name)                   : Unit
  def set(e: Effect)                 : Unit

  def help                           : String
}

//****************************************************************************

abstract class FieldOf[Value](n: Name,c: Code,val d: Value) extends Field
{
  assert(1<=n.length && n.length<=4);
  assert(-16<=c && c<=none);

  var m_val                          = d
  def name                           = n
  def code                           = c
  def dirty                          = m_val != d

  def set(v: ℝ)              : Unit = {}
  def set(v: Name)           : Unit = {}
  def set(e: Effect)         : Unit = {}

  def load(bytes:  Bytes)           = {}
  def save(bytes:  Bytes)           = {}
  def dump(writer: Writer)          = writer.append(s"$name:=$m_val")

  def put(bytes: Bytes,v: Int) =
  {
    assert(c != -16)

    if (c <= 0x0A)
    {
      assert(0<=v && v < (1<<15))

      val o = 32 + 2 * c
      bytes(o+0) = (v >> 7)  .toByte
      bytes(o+1) = (v & 0x7F).toByte
    }
    else
    {
      assert(v < (1<<8))
      val o    = 43 + c
      bytes(o) = (v & 0x7F).toByte
    }
  }

  def get(bytes: Bytes): Int =
  {
    assert(c != -16)

    if (c <= 0x0A)                                       // Occupies 2 chars?
    {
      val o = 32 + 2 * c;                                // ...compute offset

      return bytes(o)<<7 | bytes(o+1)                    // ...read, combine
    }
    else                                                  // No, one is enough
    {
      return bytes(43 + c)                               // ...read the byte
    }
  }

  def help: String = "help string"
}

//****************************************************************************
