@echo off
@Title Topvision NM3000 Server
set DIRNAME=.\

if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
%~d0
cd %DIRNAME%

@echo ֹͣNM3000����
nm3000Service.exe //SS//TopvisionNM3000
@echo ����NM3000����
nm3000Service.exe //ES//TopvisionNM3000

pause