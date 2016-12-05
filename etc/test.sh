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

dir="$(dirname $0)/../src/test/resources"

#*****************************************************************************
#**
#** 1:  test label
#** 2:  source file
#** 3:  command
#**

test-case()
{
  test=$(basename $1)
  echo "$test..."

  ./ub99 $2

  if cmp -s $1 $1.tmp
  then 
    rm $1.tmp
  else
    echo "$test failed:"
    echo "  files $test and $test.tmp differ"
  fi
}

#*****************************************************************************

test-clean()                            { rm -f $dir/*.tmp                  ;}
test-load-save()                        { test-case $1 "-l $1 -s $1.tmp"    ;}
test-read-dump()                        { test-case $1 "-r $1 -d $1.tmp $2" ;}

#*****************************************************************************

#for i in $dir/load-save-*.ub9;     do test-load-save $i;    done
 for i in $dir/read-dump-min-*.txt; do test-read-dump $i;    done
 for i in $dir/read-dump-all-*.txt; do test-read-dump $i -a; done

#*****************************************************************************
