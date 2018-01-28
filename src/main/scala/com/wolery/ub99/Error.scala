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

class Error(message: String) extends Exception(message)

//****************************************************************************

object Error extends Logging
{
  def apply(message: String): Nothing =
  {
    //log.info(message)

    throw new Error(message)
  }
}

//****************************************************************************
