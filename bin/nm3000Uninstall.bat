@echo off
@Title Topvision NM3000 Uninstall
set DIRNAME=.\

if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
%~d0
cd %DIRNAME%

@echo ж��NM3000����
nm3000Service.exe //DS//TopvisionNM3000

if not exist "..\mysql\bin\remove.bat" goto END
@echo ж�����ݿ����
SETLOCAL
call ..\mysql\bin\remove.bat
ENDLOCAL
@Title Topvision NM3000 Uninstall

:END
