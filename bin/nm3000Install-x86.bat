@echo off
@Title Topvision NM3000 Install
set DIRNAME=.\

if "%OS%" == "Windows_NT" set DIRNAME="%~dp0%\.."
%~d0
cd %DIRNAME%

setlocal enabledelayedexpansion

set MAIN=com.topvision.console.JConsoleService
set JVM=auto
set OPTIONS=--JvmMs 512 --JvmMx 1024

if NOT EXIST "jre\bin\server\jvm.dll" GOTO NEXT
set JVM=%DIRNAME%\jre\bin\server\jvm.dll
:NEXT

set CLASSPATH=conf;lib;.classes
for /f %%i in ('dir /b lib\*.jar^|sort') do (
  set CLASSPATH=!CLASSPATH!;lib\%%i
)
for /f %%i in ('dir /b lib\jetty\*.jar^|sort') do (
  set CLASSPATH=!CLASSPATH!;lib\jetty\%%i
)
echo ===============================================================================
echo.
echo   Topvision Tool Sets Server Startup Environment
echo.
echo   MAIN: %MAIN%
echo.
echo   JVM: %JVM%
echo.
echo   OPTIONS: %OPTIONS%
echo.
echo   DIRNAME: %DIRNAME%
echo.
echo   CLASSPATH: %CLASSPATH%
echo.
echo ===============================================================================

if not exist "mysql\bin\install.bat" goto NM3000
@echo 安装数据库服务
SETLOCAL
call mysql\bin\install.bat
ENDLOCAL
@Title Topvision NM3000 Install

:NM3000
@echo 安装NM3000服务
bin\nm3000Service.exe //IS//TopvisionNM3000 --DisplayName="Topvision NM3000" --StartPath=%DIRNAME% --Install=%DIRNAME%\bin\nm3000Service.exe %OPTIONS% ++JvmOptions=-XX:-OmitStackTraceInFastThrow ++JvmOptions=-Dnm3000.dongle.type=FileDongle ++JvmOptions=-XX:NewSize=128M ++JvmOptions=-XX:MaxNewSize=256M ++JvmOptions=-Dfile.encoding=UTF-8 --Startup=auto --Classpath=%CLASSPATH% --Jvm=%JVM% --StartMode=jvm --StopMode=jvm --LogPath=%DIRNAME%\logs --StdOutput=auto --StdError=auto --StartClass=%MAIN% --StartParams=start --StopClass=%MAIN% --StopParams=stop

@echo 启动NM3000服务管理
start bin\nm3000Mgr.exe //MS//TopvisionNM3000
