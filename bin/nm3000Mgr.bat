@echo off
@Title Topvision NM3000
set DIRNAME=.\

if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
%~d0
cd %DIRNAME%

@echo ����NM3000�������
start nm3000Mgr.exe //MS//TopvisionNM3000

