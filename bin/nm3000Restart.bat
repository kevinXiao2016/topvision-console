@echo off
@Title Topvision NM3000 Server
set DIRNAME=.\

if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
%~d0
cd %DIRNAME%

@echo 停止NM3000服务
nm3000Service.exe //SS//TopvisionNM3000
@echo 启动NM3000服务
nm3000Service.exe //ES//TopvisionNM3000

pause