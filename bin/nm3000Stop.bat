@echo off
@Title Topvision NM3000
set DIRNAME=.\

if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
%~d0
cd %DIRNAME%

@echo 停止NM3000服务
nm3000Service.exe //SS//TopvisionNM3000

if not exist "..\mysql\bin\stopMysql.bat" goto end
@echo 停止数据库服务
SETLOCAL
call ..\mysql\bin\stopMysql.bat
ENDLOCAL

:end
