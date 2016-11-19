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

import org.slf4j.{Logger,LoggerFactory}

//****************************************************************************

trait Logging
{
  def logName: String  = this.getClass.getName.stripSuffix("$")

  lazy val log: Logger = LoggerFactory.getLogger(logName)
}

//****************************************************************************
