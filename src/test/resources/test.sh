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
#**
#**
#*****************************************************************************

ver="1.0.0"
dir="$(dirname $0)"
tgt="$dir/../../../target"
exe="java -jar ${tgt}/ub99-${ver}.jar"

#*****************************************************************************
#**
#** 1:  test label
#** 2:  source file
#** 3:  command
#**

test-case () {
  echo "$1 $2..."

  $exe $3

  if cmp -s $2 $2.tmp
  then 
    rm $2.tmp
  else
    echo "$1 $2 failed:"
    echo "  files $(basename $2) and $(basename $2.tmp) differ"
  fi
}

#*****************************************************************************

test-clean()             { rm -f {ub9,txt,all}/*.tmp                       ;}
test-load-save()         { test-case load-save     $1 "-l $1 -s $1.tmp"    ;}
test-read-dump()         { test-case read-dump     $1 "-r $1 -d $1.tmp"    ;}
test-read-dump-all()     { test-case read-dump-all $1 "-r $1 -d $1.tmp -a" ;}

#*****************************************************************************

for i in $dir/ub9/*.ub9; do test-load-save     $i; done
for i in $dir/txt/*.txt; do test-read-dump     $i; done
for i in $dir/all/*.txt; do test-read-dump-all $i; done

#*****************************************************************************
