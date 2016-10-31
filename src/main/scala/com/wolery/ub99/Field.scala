//**************************** Copyright © Jonathon Bell. All rights reserved.
//*
//*
//*  Version : $Header:$
//*
//*
//*  Purpose : Represents a specific field within an effect.
//*
//*
//*  Comments: This file uses a tab size of 2 spaces.
//*
//*
//****************************************************************************

package com.wolery.ub99

/**
 * Represents a specific field within an effect.
 *
 * @author Jonathon Bell
 */
trait Field
{
  def name                           : Name
  def code                           : Code

  def dirty                          : Boolean

  def load(bytes:  Bytes)            : Unit
  def save(bytes:  Bytes)            : Unit
  def dump(writer: Writer)           : Unit

  def copy                           : Field

  def set(real  : ℝ)                 : Unit
  def set(name  : Name)              : Unit
  def set(effect: Effect)            : Unit

  def help                           : Unit


  def toInt                          : Int
}

//****************************************************************************
