@echo off
@Title Topvision Tool Sets
set DIRNAME=.\

if "%OS%" == "Windows_NT" set DIRNAME="%~dp0%\.."
%~d0
cd %DIRNAME%

SET LIB=%DIRNAME%\lib

set CLASSPATH=conf;%LIB%/com.topvision.launcher-1.0.1.jar;%LIB%/org.apache.felix.main-5.6.10.jar

set JVM=auto

if EXIST "jre\bin\server\jvm.dll" set JVM=%DIRNAME%\jre\bin\server\jvm.dll

echo ===============================================================================
echo.
echo   Topvision Tool Sets Server Startup Environment
echo.
echo   JVM: %JVM%
echo.
echo   DIRNAME: %DIRNAME%
echo.
echo   CLASSPATH: %CLASSPATH%
echo.
echo ===============================================================================

@echo Install NM3000 Monitor Service
monitor\monitorService.exe //IS//TopvisionToolSets --DisplayName="Topvision Tool Sets" --StartPath=%DIRNAME% --Install=%DIRNAME%\monitor\monitorService.exe --JvmMs 256 --JvmMx 512 ++JvmOptions=-Dorg.osgi.framework.bootdelegation=* --Startup=auto --Classpath=%CLASSPATH% --Jvm=%JVM% --StartMode=jvm --StopMode=jvm --LogPath=%DIRNAME%\logs --StdOutput=auto --StdError=auto --StartClass=com.topvision.launcher.TopvisionToolSets --StartParams=start --StopClass=com.topvision.launcher.TopvisionToolSets --StopParams=stop
@echo Start NM3000 Monitor Service Manager
start monitor\monitorServicew.exe //MS//TopvisionToolSets
pause
