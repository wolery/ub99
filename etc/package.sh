#!/bin/bash
#***************************** Copyright © Jonathon Bell. All rights reserved.
#**
#**
#**  Version : $Header:$
#**
#**
#**  Purpose : Binds the executable JAR to its launcher script. 
#**
#**
#**  See Also: https://coderwall.com/p/ssuaxa/how-to-make-a-jar-file-linux-executable
#**
#**
#**  Comments: This file uses a tab size of 2 spaces.
#**                                                                     0-0
#**                                                                   (| v |)
#***********************************************************************w*w***

cat etc/launch.sh target/ub99-*.jar > target/ub99 && chmod +x target/ub99

#*****************************************************************************
