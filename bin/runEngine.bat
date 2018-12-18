@echo off
set DIRNAME=.\
if "%OS%" == "Windows_NT" set DIRNAME="%~dp0%"
%~d0
cd %DIRNAME%

setlocal enabledelayedexpansion

set PORT=3010

title NM3000 engine %PORT%

set JAVA=javaw.exe
if EXIST "%JAVA_HOME%" set JAVA="%JAVA_HOME%\bin\javaw.exe"
if EXIST "..\..\jre" set JAVA=..\..\jre\bin\javaw.exe
if EXIST "..\jre" set JAVA=..\jre\bin\javaw.exe
if EXIST "jre" set JAVA=jre\bin\javaw.exe

set OPTS=-Xms1024M -Xmx8192M
set LIBPATH=lib
set CONFIG=conf
set CP=%CONFIG%
set MAIN=com.topvision.ems.engine.launcher.Collector

for /f %%i in ('dir /b %LIBPATH%\*.jar^|sort') do (
   set CP=!CP!;%LIBPATH%\%%i
)
echo ===============================================================================
echo.
echo   Topvision NM3000 Server Startup Environment
echo.
echo   JAVA: %JAVA%
echo.
echo   CONFIG: %CONFIG%
echo.
echo   JAVA_OPTS: %OPTS%
echo.
echo   CLASSPATH: %CP%
echo.
echo ===============================================================================

START %JAVA% %OPTS% -cp %CP% %MAIN% start
@echo on

