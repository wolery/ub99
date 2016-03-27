@echo off
:: ***************************  Copyright © InterSystems. All rights reserved.
:: **
:: **
:: **  Version : $Header:$
:: **
:: **
:: **  Purpose : Launches the UB99 MagicStomp Patch Editor utility.
:: **
:: **
:: **  Comments: This file uses a tab size of 3 spaces.
:: **
:: **
:: ***************************************************************************

setlocal

set JVM=-Xms1G -DIGNITE_PERFORMANCE_SUGGESTIONS_DISABLED=true
set JAR=cache-loader.jar

:: ***************************************************************************

if not defined JAVA_HOME (
   call :error Can't find the JAVA_HOME environment variable.
   goto :eof
)

if not exist "%JAVA_HOME%\bin\java.exe" (
   call :error Can't find the Java executable at JAVA_HOME=%JAVA_HOME%\bin
   goto :eof
)

"%JAVA_HOME%\bin\java.exe" -version 2>&1 | findstr 1\.[89]\.

if /i "%ERRORLEVEL%" NEQ "0" (
   call :error Can't use the Java executable at JAVA_HOME=%JAVA_HOME%\bin
   goto :eof
)

:: ***************************************************************************
"%JAVA_HOME%\bin\java.exe" %JVM% %CACHELOADER_OPTIONS% -cp %JAR%:%TGT%\test-classes %CLS% %*%
goto :eof
:: ***************************************************************************

:error
echo error:
echo.
echo   %*%
echo.
echo   This program requires Java version 1.8 or higher. If you have more
echo   than one version installed you can specify which to use by setting
echo   the JAVA_HOME environment variable.
echo.
echo   You can download the latest version from 'http://java.com/download'.
goto :eof

:: ***************************************************************************
