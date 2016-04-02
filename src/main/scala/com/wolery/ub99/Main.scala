//**************************** Copyright © Jonathon Bell. All rights reserved.
//*
//*
//*  Version : $Header:$
//*
//*
//*  Purpose : Implements the command line interface to the patch editor.
//*
//*
//*  Comments: This file uses a tab size of 2 spaces.
//*
//*
//****************************************************************************

package com.wolery.ub99

import java.io.FileInputStream
import java.io.FileOutputStream
import org.apache.commons.cli._
import java.io.PrintStream
import java.io.PrintWriter

//****************************************************************************

object Main
{
  def main(args: Array[String]): Unit =
  {
    try
    {
      val c: CommandLine = parse(args);

      if (c.hasOption('v'))
      {
        doVersion()
      }

      if (c.hasOption('h'))
      {
        doHelp()
      }
      else
      if (c.hasOption('q'))
      {
        doQuery(c.getOptionValue('q'))
      }
      else
      {
        val l = new Library();

        onPath(c,'l',p ⇒ l.load(new FileInputStream (p)))
        onPath(c,'r',p ⇒ l.read(new FileInputStream (p)))
        onPath(c,'d',p ⇒ l.dump(new PrintWriter     (p)))
        onPath(c,'s',p ⇒ l.save(new FileOutputStream(p)))
      }
    }
    catch
    {
      case e: Exception ⇒
      {
        println(e)
        println(e.getMessage)
      }
    }
  }

  def doHelp() =
  {
    println("usage: ub99 [options]")
    println("")
    println("options:")
    println(" -l,--load <path>              patch library to load")
    println(" -r,--read <path>              patch text file to read")
    println(" -d,--dump <path>              patch text file to dump")
    println(" -s,--save <path>              patch library to save")
    println(" -q,--query [effect[.field]>]  list effect types or field names")
    println(" -h,--help                     print this help message")
    println(" -q,--version                  print version information")
  }

  def doVersion() =
  {
    println("MagicStomp Patch Editing Utility v1.0.0.")
    println("Copyright © Jonathon Bell. All rights reserved.")
    println("")
    println("Creates and parses MagicStomp UB99 patch library files.")
  }

  def onPath(c: CommandLine,o: Char,act: String⇒Unit) =
  {
    if (c.hasOption(o))
    {
      val p = c.getOptionValue(o)

      try
      {
        act(p)
      }
      catch
      {
        case e: Error ⇒ e.patchAndRethrow(p)
      }
    }
  }

  def doQuery(query: String) =
  {
    query.indexOf('.') match
    {
      case -1 ⇒
      {
        val e: Effect = Effect(query)
      }
      case i ⇒
      {
        println(query.substring(0,i))
        println(query.substring(i+1))
      }
    }
  }

  def parse(args: Array[String]): CommandLine =
  {
    new DefaultParser().parse(new Options()
    .addOption(new Option("l","load",   true, "patch library to load"))
    .addOption(new Option("r","read",   true, "patch text file to read"))
    .addOption(new Option("d","dump",   true, "patch text file to dump"))
    .addOption(new Option("s","save",   true, "patch library to save"))
    .addOption(new Option("q","query",  true, "list effect types or field names"))
    .addOption(new Option("h","help",   false,"prints this help message"))
    .addOption(new Option("v","version",false,"prints version information"))
    ,args);
  }
}

//****************************************************************************
