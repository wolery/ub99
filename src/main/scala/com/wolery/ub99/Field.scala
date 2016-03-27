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

import update.update
import java.io.Writer
import java.io.OutputStream
import java.io.InputStream

//****************************************************************************

trait Field //(val name: Name,val code: Code)
{
  def name: Name
  def code: Code

  def dirty                          : Bool
  def effect(e: Effect)              : Unit = {}

  def load(io: Array[Byte])          : Unit
  def save(io: Array[Byte])          : Unit
  def dump(io: Writer)               : Unit

  def set(v: Double): Unit = {}
  def set(v: Name)  : Unit = {}
}

//****************************************************************************
