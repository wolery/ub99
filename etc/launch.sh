#!/bin/bash
#***************************** Copyright © Jonathon Bell. All rights reserved.
#**
#**
#**  Version : $Header:$
#**
#**
#**  Purpose : Generic JAR launcher script.
#**
#**
#**  Usage   : 1. Create an executable JAR file  e.g. foo.jar.
#**            2. Append it to this script       e.g. cat launch.sh foo.jar > foo
#**            3. Mark resulting file executable e.g. chmod +x foo
#**
#**
#**  See Also: https://coderwall.com/p/ssuaxa/how-to-make-a-jar-file-linux-executable
#**
#**
#**  Comments: This file uses a tab size of 2 spaces.
#**
#**
#*****************************************************************************

error()
{
  echo $0":"
  echo 
  echo "  "$1
  echo 
  echo "  This program requires Java version 1.8 or higher. If you have more" 
  echo "  than one version installed you can specify which to use by setting"
  echo "  the JAVA_HOME environment variable."
  echo
  echo "  You can download the latest version from '  '."
  exit 1
}

if [ "$JAVA_HOME" = "" ]; then
  JAVA=$(type -p java)

  if [ $? -ne 0 ]; then
    error "Can't find the Java executable."
  fi
else
  JAVA=$JAVA_HOME/bin/java
fi

if [ ! -e "$JAVA" ]; then
  error "Can't find the Java executable at JAVA_HOME=$JAVA_HOME/bin"
fi

if [ "`$JAVA -version 2>&1 | egrep 1\.[89]\.`" == "" ]; then
  error "Can't use the Java executable at JAVA_HOME=${JAVA_HOME}/bin"
fi

JAR=$(which "$0" 2>/dev/null)

[ $? -gt 0 -a -f "$0" ] && JAR="./$0"

exec $JAVA -jar $JAR "$@"
exit 1 

#*****************************************************************************
