//**************************** Copyright © Jonathon Bell. All rights reserved.
//*
//*
//*  Version : $Header:$
//*
//*
//*  Purpose : Miscellaneous utility functions.
//*
//*
//*  Comments: This file uses a tab size of 2 spaces.
//*
//*
//****************************************************************************

package com.wolery.ub99

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Miscellaneous utility functions.
 *
 * @author Jonathon Bell
 */
object Utilities
{
  /**
   * Rounds the given real off to the nearest integer.
   *
   * @param real
   *
   * @return
   */
  def round(real: ℝ): Int = (real + 0.5).toInt

  def inside(value: ℝ,lo: ℝ,hi:ℝ): Boolean =
  {
    lo.compareTo(value) != -value.compareTo(hi)
  }

  def outside(value: ℝ,lo: ℝ,hi:ℝ): Boolean =
  {
    lo.compareTo(value) == -value.compareTo(hi)
  }

  /**
   *
   * @param bytes
   * @param offset
   * @param length
   *
   * @return
   */
  def substring(bytes: Bytes,offset: ℕ,length: ℕ): String =
  {
    (new String(bytes.slice(offset,offset + length))).intern
  }


  /**
   * Print the given sequence of strings to the command line, formatted as a
   * table with 3 columns, each 20 characters wide.
   */
  def tabulate(strings: Seq[String]) =
  {
    val s = strings.sorted :+ "" :+ ""                 // Add two sentinels
    val n = s.size / 3                                 // Number iterations

    for (i ← 0 until n)                                // For third of array
    {
      printf("%-20s%-20s%-20s\n",s(i),s(i+n),s(i+2*n)) // ...print 3 columns
    }
  }

  /**
   * @param frequency
   *
   * @return
   */
  def Hz(frequency: Double): String =
  {
    if (frequency >= 1000)
      f"${frequency/1000}%.2fkHz"
    else
      f"${frequency}%.2fHz"
  }

  def replace[T](sequence: Seq[T],add: T,remove: T*): Seq[T] =
  {
    Seq(add) ++ sequence.diff(remove)
  }

}

//****************************************************************************
