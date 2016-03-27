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
//*
//*
//****************************************************************************

package com.wolery.ub99

import Fields._
import Effects._
import java.io.Writer
import scala.collection.immutable.TreeMap
import java.io.InputStream
import java.io.OutputStream

//****************************************************************************

class Effect (val kind: Kind,val name: Name,val fields: Seq[Field])
{
  val m_name: Map[Name,Field]    = TreeMap(fields.map(f ⇒ f.name→f):_*)
  val m_code: Map[Code,Field]    = TreeMap(fields.map(f ⇒ f.code→f):_*)

  def field(name: Name): Field   = m_name(name)
  def field(code: Code): Field   = m_code(code)

  /**
   *
   */
  def load(io: InputStream) =
  {

  }

  /**
   *
   */
  def save(io: OutputStream) =
  {

  }

  /**
   *
   */
  def dump(io: Writer) =
  {
    var first = true

    io.append(f"$name%-19s(")

    for (f ← fields)
    {
     // if (f.dirty)
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

  override
  def clone: Effect =
  {
    new Effect(kind,name,fields)
  }


}

//****************************************************************************

object Effect
{
  def apply(): Effect =
  {
    val e = Effect("Amp")
    e.field("AMP" ).set("Solid")
    e.field("GAIN").set(0.5)
    e.field("MSTR").set(0.5)
    e
  }

  def apply(name: Name): Effect = Effects.byname(name).clone
  def apply(kind: Kind): Effect = Effects.bykind(kind).clone

  /**
   *
   */
  def apply(bytes: Array[Byte]): Effect =
  {
    val e = Effect(bytes(1))

    e.fields.foreach(f ⇒ f.load(bytes))

    e
  }

  /**
   *
   */
  def apply(kind: Kind,name: Name,k1: Name,k2: Name,k3: Name,fs: Field*): Effect =
  {
    val k = Seq(newName("NAME",-16,"            "),
                newKnob("KNB1",-15,k1),
                newKnob("KNB2",-14,k2),
                newKnob("KNB3",-13,k3))

    val e = new Effect(kind,name,k ++ fs)

    k.foreach(f ⇒ f.effect(e))
    e
  }
}

//****************************************************************************
