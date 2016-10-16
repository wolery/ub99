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

/**
 * @author Jonathon Bell
 */
object Main
{
  /**
   * Parse the command line, test for options, and drive the entire show.
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
      }
      else
      if (c.hasOption('v'))                              // Asking for version?
      {
        doVersion()                                      // ...display version
      }
      else
      if (c.hasOption('q'))                              // Asking for effects?
      {
        c.getOptionValue('q') match                      // ...specific effect?
        {
          case null  ⇒ doQuery()                         // ...n: list all
          case query ⇒ doQuery(query)                    // ...y: list fields
        }
      }
      else
      {
        val l: Library = new Library();                  // ...default library

        onPath(c,'l',p ⇒ l.load(new FileInputStream (p)))// ...load library
        onPath(c,'r',p ⇒ l.read(new FileInputStream (p)))// ...read text file
        onPath(c,'d',p ⇒ l.dump(new PrintWriter     (p)))// ...dump text file
        onPath(c,'s',p ⇒ l.save(new FileOutputStream(p)))// ...save library
      }
    }
    catch                                                // Operation failed
    {
      case e: Throwable ⇒                                // ...catch everything
      {
        println(e.getMessage)                            // ....display error
        sys.exit(1)                                      // ....exit with code
      }
    }
  }

  /**
   * Display the usage string on the command line.
   */
  def doHelp() =
  {
    println("Usage: ub99 [options]")
    println("Creates and parses MagicStomp UB99 patch library files.")
    println("")
    println("Options:")
    println(" -l,--load <path>              patch library to load")
    println(" -r,--read <path>              patch text file to read")
    println(" -d,--dump <path>              patch text file to dump")
    println(" -s,--save <path>              patch library to save")
    println(" -q,--query [effect[.field]>]  list effect types or field names")
    println(" -h,--help                     print this help message")
    println(" -v,--version                  print version information")
  }

  /**
   * Display the version string on the command line.
   */
  def doVersion() =
  {
    println("MagicStomp Patch Editing Utility v1.0.0.")
    println("Copyright © Jonathon Bell. All rights reserved.")
  }

  /**
   * List the effects, the fields of a specific effect, or the help string for
   * a specific field of a specific effect.
   *
   * @param query  an optional query string of the form `effect[.field]`
   */
  def doQuery(query: String = "") =
  {
    val (n,f) = query.span(_ != '.')                     // Parse "effect.field"

    try                                                  // The lookup may fail
    {
      val e = Effect(n)                                  // ...lookup by name

      try                                                // ...lookup may fail
      {
        println(e(f.drop(1)).help)                       // ....look for field
      }
      catch                                              // ...no such field
      {
        case _: NoSuchElementException ⇒ print(e.help)   // ...list all fields
      }
    }
    catch                                                // No such effect
    {
      case _: NoSuchElementException⇒print(Effects.help) // ...list all effects
    }

    /**
     * Print the given sequence of strings to the command line, formatted as a
     * table with 3 columns, each 20 characters wide.
     */
    def print(strings: Seq[String]) =
    {
      val s = strings :+ "" :+ ""                        // Add two sentinels
      val n = s.size / 3                                 // Number iterations

      for (i ← 0 until n)                                // For third of array
      {
        printf("%-20s%-20s%-20s\n",s(i),s(i+n),s(i+2*n)) // ...print 3 columns
      }
    }
  }

  /**
   * Test the command line for an option that takes a required 'path' argument
   * and if present apply the given function to its path. If the action fails,
   * then fix up the error message to include the path that caused the problem
   * and re-throw the exception.
   *
   * @param cl      the parsed command line
   * @param option  an option that requires a path argument
   * @param action  an action to apply to the option's path
   */
  def onPath(cl: CommandLine,option: Char,action: String⇒Unit) =
  {
    if (cl.hasOption(option))                            // Option specified?
    {
      val p = cl.getOptionValue(option)                  // ...path argument

      try                                                // ...action may fail
      {
        action(p)                                        // ....perform action
      }
      catch                                              // ...action failed?
      {
        case e: Error ⇒ e.patchAndRethrow(p)             // ....fix up message
      }
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
      def add(s: String,l: String,f: Option.Builder⇒Option.Builder = identity): Options =
        o.addOption(f(Option.builder(s).longOpt(l)).build)
    }

    new DefaultParser().parse(new Options()
      .add("l","load"   ,_.hasArg)                       // ...requires path
      .add("r","read"   ,_.hasArg)                       // ...requires path
      .add("d","dump"   ,_.hasArg)                       // ...requires path
      .add("s","save"   ,_.hasArg)                       // ...requires path
      .add("q","query"  ,_.hasArg.optionalArg(true))     // ...optional query
      .add("h","help"   )                                // ...simple switch
      .add("v","version")                                // ...simple switch
      ,args)
  }
}

//****************************************************************************
