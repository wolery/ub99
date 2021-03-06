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
//*                                                                     0-0
//*                                                                   (| v |)
//**********************************************************************w*w***

package com.wolery
package ub99

//****************************************************************************

import java.io._
import scala.sys.exit
import org.apache.commons.cli._
import org.apache.commons.cli.Option._

import com.wolery.util.manifest.println
import Library._

//****************************************************************************

/**
 * @author Jonathon Bell
 */
object ub99
{
  /**
   * Parse the command line, test for options, and generally run the show.
   *
   * @param args  the command line argument strings
   */
  def main(args: Array[String]): Unit =
  {
    try
    {
      val c: CommandLine = parse(args);                  // Parse command line

      if (c.hasOption('h'))                              // Asking for help?
      {
        doHelp()                                         // ...display help
        exit(0)                                          // ...exit success
      }

      if (c.hasOption('v'))                              // Asking for version?
      {
        doVersion()                                      // ...display version
        exit(0)                                          // ...exit success
      }

      if (c.hasOption('q'))                              // Asking for effects?
      {
        c.getOptionValue('q') match                      // ...specific effect?
        {
          case null  ⇒ doQuery()                         // ...n: list all
          case query ⇒ doQuery(query)                    // ...y: list fields
        }
        exit(0)                                          // ...exit success
      }

      if (c.hasOption('a'))                              // Want every field?
      {
        all_fields = true                                // ...set global flag
      }

      onPath(c,'l',p⇒load(new FileInputStream(p)), "error loading patch library")
      onPath(c,'s',p⇒save(new FileOutputStream(p)),"error saving patch library")
      onPath(c,'r',p⇒read(new FileReader(p)),      "error reading patch edit file")
      onPath(c,'d',p⇒dump(new FileWriter(p)),      "error dumping patch edit file")
    }
    catch                                                // Operation failed
    {
      case e: Error ⇒                                    // ...catch exception
      {
        println(s"ub99: ${e.getMessage}")                // ....display error
        exit(1)                                          // ....exit with code
      }
      case e: Exception ⇒                                // ...catch exception
      {
        e.printStackTrace()                              // ....
        println(s"ub99: ${e.getMessage}")                // ....display error
        exit(2)                                          // ....exit with code
      }
    }
  }

  /**
   * Test the command line for an option that takes a required 'path' argument
   * and if present apply the given function to its path. If the action fails,
   * then fix up the error message to include the path that caused the problem
   * and re-throw the exception.
   *
   * @param cl       the parsed command line
   * @param option   an option that requires a path argument
   * @param action   an action to apply to the option's path
   * @param string
   */
  def onPath(cl: CommandLine,option: Char,open: String ⇒ Unit,string: String): Unit =
  {
    if (cl.hasOption(option))
    {
      val path = cl.getOptionValue(option)

      try open(path) catch
      {
        case e: IOException ⇒ Error(s"$string '$path': ${e.getMessage}")
      }
    }
  }

  /**
   * Display the usage string back on the command line.
   */
  def doHelp() =
  {
    println("Usage: ub99 [options]")
    println("Creates and parses MagicStomp UB99 patch library files.")
    println("See ${Implementation-URL} for more details.")
    println("")
    println("Options:")
    println(" -l,--load <path>              patch library to load")
    println(" -r,--read <path>              patch text file to read")
    println(" -d,--dump <path>              patch text file to dump")
    println(" -s,--save <path>              patch library to save")
    println(" -q,--query [effect[.field]>]  query effect types/field names")
    println(" -a,--all-fields               include all field values in dump")
    println(" -h,--help                     print this help message")
    println(" -v,--version                  print version information")
  }

  /**
   * Display the version string back on the command line.
   */
  def doVersion() =
  {
    println("${Description} v${Implementation-Version} [${Profile-Id}]")
    println("Built on ${Built}.")
    println("Copyright © Jonathon Bell. All rights reserved.")
    println("See ${Implementation-URL} for more details.")
  }

  /**
   * List the effects, the fields of a specific effect, or the help string for
   * a specific field of a specific effect.
   *
   * @param query  an optional query string of the form `effect[.field]`
   */
  def doQuery(query: String = "") =
  {
    val (n,f) = query.span(_ != '.')                     // Effect/field names

    try                                                  // The lookup may fail
    {
      val e = Effect(n)                                  // ...lookup by name

      try                                                // ...lookup may fail
      {
        e(f.drop(1)).help                                // ....look for field
      }
      catch                                              // ...no such field
      {
        case _: NoSuchElementException ⇒ e.help          // ...list all fields
      }
    }
    catch                                                // No such effect
    {
      case _: NoSuchElementException ⇒ Effects.help      // ...list all effects
    }
  }

  /**
   * Parse the given array of command line arguments, and return the result of
   * the parse as an instance of class `CommandLine`.
   *
   * @param args  the command line argument strings as handed to `main`
   */
  def parse(args: Array[String]): CommandLine =
  {
    implicit class OptionsEx(o: Options)
    {
      def add(s: String,l: String,f: Builder ⇒ Builder = identity): Options =
        o.addOption(f(builder(s).longOpt(l)).build)
    }

    new DefaultParser().parse(new Options()
      .add("l","load" ,_.hasArg)                         // ...requires path
      .add("r","read" ,_.hasArg)                         // ...requires path
      .add("d","dump" ,_.hasArg)                         // ...requires path
      .add("s","save" ,_.hasArg)                         // ...requires path
      .add("q","query",_.hasArg.optionalArg(true))       // ...optional query
      .add("a","all-fields")                             // ...simple switch
      .add("h","help")                                   // ...simple switch
      .add("v","version")                                // ...simple switch
      ,args)                                             // Parse arguments
  }
}

//****************************************************************************
