@echo off
@Title Topvision NM3000
set DIRNAME=.\

if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
%~d0
cd %DIRNAME%

if not exist "..\mysql\bin\startMysql.bat" goto NM3000
@echo 启动数据库服务
SETLOCAL
call ..\mysql\bin\startMysql.bat
ENDLOCAL

:NM3000
@echo 启动NM3000服务
nm3000Service.exe //ES//TopvisionNM3000
