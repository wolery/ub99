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
//*
//*
//****************************************************************************

package com.wolery.ub99

//****************************************************************************

object Utils
{
/**
 *  Round the real number 'reall' off to the nearest integer.
 */
def round(real: ℝ): Int = (real + 0.5).toInt

/**
 * True if the real numbers 'r1' and 'r2' differ by only a teensy weensy bit.
 */
//def almostEqual(r1: ℝ,r2: ℝ): Bool = Double.abs(r2 - r1) < 0.00001;


def substring(bytes: Bytes,o:Int,n:Int): String =
{
  (new String(bytes.slice(o,o+n))).intern
}

}

//****************************************************************************
