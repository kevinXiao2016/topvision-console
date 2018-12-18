@echo off
@Title Topvision Tool Sets
set DIRNAME=.\

if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
%~d0
cd %DIRNAME%

@echo 启动Tool Sets监视器服务
monitorService.exe //ES//TopvisionToolSets

pause