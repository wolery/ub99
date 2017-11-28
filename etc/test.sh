#!/bin/bash
#***************************** Copyright © Jonathon Bell. All rights reserved.
#**
#**
#**  Version : $Header:$
#**
#**
#**  Purpose : 
#**
#**
#**  Comments: This file uses a tab size of 2 spaces.
#**                                                                     0-0
#**                                                                   (| v |)
#***********************************************************************w*w***

dir="$(dirname $0)/../src/test/resources"

#*****************************************************************************
#**
#** 1:  artifact 

compare ()
{
  if cmp -s $1 $2 ; then
   rm $2
  else
    echo "$test failed:"
    echo "  files $(basename $1) and $(basename $2) differ"
  fi
}

#*****************************************************************************
#**
#** 1:  test label = subdirectoy

test-case()
{
  test=$(basename $1)

  load=$1$test.l.ub9
  save=$1$test.s.ub9 && [ ! -e $save ] && save=$load
  read=$1$test.r.txt
  dump=$1$test.d.txt
  Dump=$1$test.a.txt

  echo "$test..."
 
  c="./ub99"

  [ -f $load ] && c="$c -l $load"
  [ -f $read ] && c="$c -r $read"
  [ -f $save ] && c="$c -s $save.ub9"

  cd="$c -d $dump.txt"
  ca="$c -d $Dump.txt -a"

  if [ -f $dump ] ; then
     $cd && [ -f $Dump ] && $ca
  else
     [   -f $Dump ] && $ca
     [ ! -f $Dump ] && $c
  fi

  [ -f $save ] && compare $save $save.ub9
  [ -f $dump ] && compare $dump $dump.txt
  [ -f $Dump ] && compare $Dump $Dump.txt
}

#*****************************************************************************

for d in $dir/*/ ; do test-case $d ; done

#*****************************************************************************
