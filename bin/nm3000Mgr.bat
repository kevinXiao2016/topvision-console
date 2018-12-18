@echo off
@Title Topvision NM3000
set DIRNAME=.\

if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
%~d0
cd %DIRNAME%

@echo 启动NM3000服务管理
start nm3000Mgr.exe //MS//TopvisionNM3000

