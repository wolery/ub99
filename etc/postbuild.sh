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

echo "Binding jar file to launcher script..."

cat etc/launch.sh target/ub99-*.jar > ub99 && chmod +x ub99

#*****************************************************************************