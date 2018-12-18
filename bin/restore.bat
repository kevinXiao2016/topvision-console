@echo off
set BAKDIR=.\

if "%OS%" == "Windows_NT" set BAKDIR=%~dp0%
%~d0
cd %BAKDIR%

rem Uninstall NM3000 Service
SETLOCAL
call ..\bin\nm3000Uninstall.bat
ENDLOCAL

rem copy the database restore sql
mkdir webapp\WEB-INF\classes\com\topvision\ems\databaserollback
copy /Y ..\webapp\META-INF\script\rollback-lasted.sql webapp\WEB-INF\classes\com\topvision\ems\databaserollback\database-data-mysql.sql

rem Delete the update and restore the old version files
rd /s/q ..\bin
rd /s/q ..\jre
rd /s/q ..\lib
rd /s/q ..\webapp
rd /s/q ..\tmp
move /Y bin ..\bin
move /Y jre ..\jre
move /Y lib ..\lib
move /Y webapp ..\webapp

rem Modify the reg to old version
REG IMPORT version.reg

rem Install and start NM3000 Service 
SETLOCAL
call ..\bin\nm3000Install.bat
ENDLOCAL

SETLOCAL
call ..\bin\nm3000Start.bat
ENDLOCAL

rem Delete backup files
rem cd %BAKDIR%\..
rem rd /s/q backup
pause
