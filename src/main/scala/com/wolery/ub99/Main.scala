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

      if (c.hasOption('h'))
      {
        doHelp
      }
      else
      if (c.hasOption('v'))
      {
        doVersion
      }
      else
      if (c.hasOption('q'))
      {
        doQuery(c.getOptionValue('q'))
      }
      else
      {
        val l: Library = new Library();

        if (c.hasOption('l'))
        {
          l.load(new FileInputStream(c.getOptionValue('l')))
        }

        if (c.hasOption('r'))
        {
          l.read(new FileInputStream(c.getOptionValue('r')))
        }

        if (c.hasOption('d'))
        {
          l.dump(new PrintWriter(c.getOptionValue('d')))
        }

        if (c.hasOption('s'))
        {
          l.save(new FileOutputStream(c.getOptionValue('s')))
        }
      }
    }
    catch
    {
      case e: Error ⇒
      {
        println(e("A","B","C","D"))
      }
      case e: Exception ⇒
      {
        println(e)
        println(e.getMessage)
      }
    }
  }

  def doHelp() =
  {
    println("usage: ub99")
    println("")
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
    println("Creates and parses MagicStomp (.ub9) patch library files.")
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
    new DefaultParser().parse(options,args);
  }

  def options(): Options =
  {
    new Options()
      .addOption(
          Option.builder     ("l")
                .longOpt     ("load")
                .desc        ("patch library to load")
                .argName     ("path")
                .numberOfArgs(1)
                .build())
      .addOption(
          Option.builder     ("r")
                .longOpt     ("read")
                .desc        ("patch text file to read")
                .argName     ("path")
                .numberOfArgs(1)
                .build())
      .addOption(
          Option.builder     ("d")
                .longOpt     ("dump")
                .desc        ("patch text file to dump")
                .argName     ("path")
                .numberOfArgs(1)
                .build())
      .addOption(
          Option.builder     ("s")
                .longOpt     ("save")
                .desc        ("patch library to save")
                .argName     ("path")
                .numberOfArgs(1)
                .build())
      .addOption(
          Option.builder     ("q")
                .longOpt     ("query")
                .desc        ("list effect types or field names")
                .argName     ("[effect.field]")
                .numberOfArgs(1)
                .build())
      .addOption(
          Option.builder     ("h")
                .longOpt     ("help")
                .desc        ("prints this help message")
                .build())
      .addOption(
          Option.builder     ("v")
                .longOpt     ("version")
                .desc        ("prints version information")
                .build())
  }
}

//****************************************************************************
