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
//*                                                                     0-0
//*                                                                   (| v |)
//**********************************************************************w*w***

package com.wolery
package ub99

/**
 * Represents a specific field within an effect.
 *
 * @author Jonathon Bell
 */
trait Field extends Cloneable
{
  def name                           : Name
  def code                           : Code

  def help()                         : Unit
  def copy                           : Field

  def dirty                          : Boolean

  def load(bytes:  Bytes)            : Unit
  def save(bytes:  Bytes)            : Unit
  def dump(writer: Writer)           : Unit

  def increment(value: ℝ)            : Boolean
  def decrement(value: ℝ)            : Boolean
  def overwrite(value: ℝ)            : Boolean
  def overwrite(value: Name)         : Boolean

  def set(effect: Effect)            : Unit
}

//****************************************************************************
