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
package ub99

//****************************************************************************

case class Hz (Hz: ℝ)
{
  assert(Hz > 0,"non-positive Hz")

  def kHz: ℝ = Hz * 1e-3

  override
  def equals(any: Any) = any match
  {
    case that: Hz ⇒ Math.abs(this.Hz - that.Hz) < 1e-2
    case _        ⇒ false
  }

  override
  def toString: String = if (kHz >= 1) f"$kHz%.2fkHz" else f"$Hz%.2fHz"
}

object Hz
{
  import scala.language.implicitConversions

  implicit def hertz(hz: ℝ): Hz = Hz(hz)
}
//****************************************************************************
