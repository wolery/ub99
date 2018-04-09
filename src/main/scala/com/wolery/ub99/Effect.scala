//**************************** Copyright © Jonathon Bell. All rights reserved.
//*
//*
//*  Version : $Header:$
//*
//*
//*  Purpose : Represents an effect as a collection of fields.
//*
//*
//*  Comments: This file uses a tab size of 2 spaces.
//*                                                                     0-0
//*                                                                   (| v |)
//**********************************************************************w*w***

package com.wolery
package ub99

import scala.collection.immutable.TreeMap

import Fields.newKnob
import Fields.newName
import Utilities.tabulate

//****************************************************************************

case class Effect (kind: Kind,name: Name,fields: Seq[Field])
{
  val m_name: Map[Name,Field]    = TreeMap(fields.map(f ⇒ f.name.toUpperCase→f):_*)
  val m_code: Map[Code,Field]    = TreeMap(fields.map(f ⇒ f.code→f):_*)

  def apply(name: Name): Field   = m_name(name.toUpperCase)
  def apply(code: Code): Field   = m_code(code)
  def names            : Seq[Name] = fields.map(f ⇒ f.name)
  def help             : Unit    = tabulate(names)

  def patchName: Name =
  {
    val sw = new java.io.StringWriter()
    apply(-16).dump(sw)
    sw.toString.substring(1,11).trim().intern
  }

  def save(bytes: Bytes) =
  {
    bytes(1) = kind

    fields.foreach(_.save(bytes))
  }

  def dump(io: Writer) =
  {
    var first = true

    io.append(f"$name%-19s(")

    for (f ← fields)
    {
      if (f.dirty || all_fields)
      {
        if (first)
        {
          first = false;
        }
        else
        {
          io.append(',')
        }

        f.dump(io)
      }
    }

    io.append(')')
  }

  def dump(io: Writer,slot: Slot,effect: Effect) =
  {
    assert(this.kind == effect.kind)

    var first = true

    io.append(f"${slot+1}%-19d(")

    for (f ← fields)
    {
      if (!f.equals(effect(f.code)))
      {
        if (first)
        {
          first = false;
        }
        else
        {
          io.append(',')
        }

        f.dump(io)
      }
    }

    io.append(')')
  }

  def copy: Effect =
  {
    new Effect(kind,name,fields.map(_.copy))
  }

  def isDefault: Boolean =
  {
    equals(Effect.default)
  }

  override
  def toString: String = name
}

//****************************************************************************

object Effect
{
  val default =
  {
    val e = Effect("Amp")
    e("AMP" ).overwrite("Solid")
    e("GAIN").overwrite(5.0)
    e("MSTR").overwrite(5.0)
    e
  }

  def apply(): Effect =
  {
    default.copy
  }

  def apply(name: Name): Effect =
  {
    Effects.byname(name.toUpperCase).copy
  }

  def apply(kind: Kind): Effect =
  {
    Effects.bykind(kind).copy
  }

  def apply(bytes: Bytes): Effect =
  {
    val e = Effect(bytes(1))

    e.fields.foreach(f ⇒ f.load(bytes))

    e
  }

  def apply(kind: Kind,name: Name,k1: Name,k2: Name,k3: Name,fs: Field*): Effect =
  {
    val k = Seq(newName("NAME",-16," " * name_size),
                newKnob("KNB1",-15,k1),
                newKnob("KNB2",-14,k2),
                newKnob("KNB3",-13,k3))

    val e = new Effect(kind,name,k ++ fs)

    k.foreach(f ⇒ f.set(e))
    e
  }
}

//****************************************************************************
