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

class Error (val format: String) extends Exception
{
  def apply(args: String*): String = ???
  def apply(args: Map[String,String]): String = ???
}

//****************************************************************************

object Error
{
  def apply(format: String) = new Error(format)
}

//****************************************************************************
