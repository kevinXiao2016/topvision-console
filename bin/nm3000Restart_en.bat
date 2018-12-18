@echo off
@Title Topvision NM3000 Server
set DIRNAME=.\

if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
%~d0
cd %DIRNAME%

@echo Stop NM3000 Service
nm3000Service.exe //SS//TopvisionNM3000
@echo Start NM3000 Service
nm3000Service.exe //ES//TopvisionNM3000

pause