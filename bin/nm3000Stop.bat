@echo off
@Title Topvision NM3000
set DIRNAME=.\

if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
%~d0
cd %DIRNAME%

@echo ֹͣNM3000����
nm3000Service.exe //SS//TopvisionNM3000

if not exist "..\mysql\bin\stopMysql.bat" goto end
@echo ֹͣ���ݿ����
SETLOCAL
call ..\mysql\bin\stopMysql.bat
ENDLOCAL

:end
