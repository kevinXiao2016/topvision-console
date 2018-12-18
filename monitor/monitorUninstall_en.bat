@echo off
@Title Topvision Tool Sets
set DIRNAME=.\

if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
%~d0
cd %DIRNAME%

@echo Uninstall Tool Sets Service
monitorService.exe //DS//TopvisionToolSets

pause