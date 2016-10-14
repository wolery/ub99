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

import java.io._
import org.apache.commons.cli._

//****************************************************************************

object Main
{
  def main(args: Array[String]): Unit =
  {
    try
    {
      val c: CommandLine = parse(args);
      val l: Library     = new Library();

      if (c.hasOption('h'))
      {
        doHelp()
      }
      else
      if (c.hasOption('v'))
      {
        doVersion()
      }
      else
      if (c.hasOption('q'))
      {
        doQuery(c.getOptionValue('q'))
      }
      else
      {
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
        System.exit(1)
      }
    }
  }

  def doHelp() =
  {
    println("Usage: ub99 [options]")
    println("Creates and parses MagicStomp UB99 patch library files.")
    println("")
    println("Options:")
    println(" -l,--load <path>                patch library to load")
    println(" -r,--read <path>                patch text file to read")
    println(" -d,--dump <path>                patch text file to dump")
    println(" -s,--save <path>                patch library to save")
    println(" -q,--query [effect[.field]>]    list effect types or field names")
    println(" -h,--help                       print this help message")
    println(" -v,--version                    print version information")
  }

  def doVersion() =
  {
    println("MagicStomp Patch Editing Utility v1.0.0.")
    println("Copyright © Jonathon Bell. All rights reserved.")
  }

  /**
   * @param query
   */
  def doQuery(query: String) =
  {
    val (n,f) = query.span(_ != '.')

    try
    {
      val e = Effect(n)

      try
      {
        println(e(f.drop(1)).help)
      }
      catch
      {
        case _: NoSuchElementException ⇒ write(e.help)
      }
    }
    catch
    {
      case _: NoSuchElementException   ⇒ write(Effects.help)
    }

    def write(seq: Seq[String]) =
    {
      val s = if (seq.size % 2 == 0) seq else seq :+ ""
      val n = s.size / 2

      for (i ← 0 until n)
      {
        println(f"${s(i)}%-20s ${s(n+i)}%s")
      }
     }
  }

  def onPath(c: CommandLine,option: Char,action: String⇒Unit) =
  {
    if (c.hasOption(option))
    {
      val p = c.getOptionValue(option)

      try
      {
        action(p)
      }
      catch
      {
        case e: Error ⇒ e.patchAndRethrow(p)
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
    .addOption(new Option("h","help",   false,"print this help message"))
    .addOption(new Option("v","version",false,"print version information"))
    ,args);
  }
}

//****************************************************************************
