@echo off
@Title Topvision NM3000
set DIRNAME=.\

if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
%~d0
cd %DIRNAME%

nm3000Service.exe //TS//TopvisionNM3000

pause