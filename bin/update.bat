@echo off
@Title Topvision NM3000 Update
set DIRNAME=.\

if "%OS%" == "Windows_NT" set DIRNAME="%~dp0%\.."
%~d0
cd %DIRNAME%

setlocal enabledelayedexpansion

set MAIN=com.topvision.ems.admin.action.Update

set JAVA=java.exe

if NOT EXIST "jre\bin\java.exe" GOTO NEXT
set JAVA=jre\bin\java.exe
:NEXT

set CLASSPATH=conf;lib;.classes
for /f %%i in ('dir /b webapp\WEB-INF\lib\topvision-admin-*.jar^|sort') do (
  set CLASSPATH=!CLASSPATH!;webapp\WEB-INF\lib\%%i
)
for /f %%i in ('dir /b lib\*.jar^|sort') do (
  set CLASSPATH=!CLASSPATH!;lib\%%i
)
echo ===============================================================================
echo.
echo   Topvision Update Startup Environment
echo.
echo   MAIN: %MAIN%
echo.
echo   JAVA: %JAVA%
echo.
echo   DIRNAME: %DIRNAME%
echo.
echo   CLASSPATH: %CLASSPATH%
echo.
echo ===============================================================================

%JAVA% -classpath %CLASSPATH% %MAIN%

pause
