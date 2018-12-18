@echo off
@Title Topvision NM3000
set DIRNAME=.\

if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
%~d0
cd %DIRNAME%

if not exist "..\mysql\bin\startMysql.bat" goto NM3000
@echo Start Mysql Service
SETLOCAL
call ..\mysql\bin\startMysql.bat
ENDLOCAL

:NM3000
@echo Start NM3000 Service
nm3000Service.exe //ES//TopvisionNM3000
