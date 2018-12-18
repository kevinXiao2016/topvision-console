@echo off
@Title Topvision Tool Sets
set DIRNAME=.\

if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
%~d0
cd %DIRNAME%

@echo Start Tool Sets Service
monitorService.exe //ES//TopvisionToolSets

pause