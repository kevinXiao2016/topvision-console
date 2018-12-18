@echo off
set DIRNAME=.\
if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
%~d0
cd %DIRNAME%\..

if EXIST "felix-cache" rmdir /S/Q felix-cache

setlocal enabledelayedexpansion

set JAVA=java
if EXIST "%JAVA_HOME%" set JAVA="%JAVA_HOME%\bin\java"
if EXIST "jre" set JAVA=jre\bin\java
set OPTS=-Dorg.osgi.framework.bootdelegation=*
set CP=conf;lib;lib/com.topvision.launcher-1.0.1.jar;lib/org.apache.felix.main-5.6.10.jar
set MAIN=com.topvision.launcher.TopvisionToolSets
echo ===============================================================================
echo.
echo   Topvision Tool Sets Server Startup Environment
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

%JAVA% %OPTS% -cp %CP% %MAIN%
pause
@echo on