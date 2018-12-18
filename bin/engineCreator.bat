@echo off
@Title Engine Creator
set DIRNAME=.\

if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
%~d0
cd %DIRNAME%

if not exist "..\webapp\META-INF\engine" goto NEXT
rd /s/q ..\webapp\META-INF\engine
:NEXT

mkdir ..\webapp\META-INF\engine
cd ..\webapp\META-INF\engine
mkdir .\bin
xcopy ..\..\..\bin\runEngine.* .\
xcopy ..\..\..\bin\diskspace.exe .\bin\
xcopy ..\..\WEB-INF\conf\engine.properties conf\
xcopy ..\..\WEB-INF\conf\quartz.properties conf\
xcopy ..\..\WEB-INF\conf\logback.xml conf\

xcopy ..\..\WEB-INF\dll .\dll\ /E/Y
xcopy ..\..\WEB-INF\mibs .\mibs\ /E/Y

xcopy ..\..\WEB-INF\lib\aopalliance-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\commons-logging-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\com.topvision.*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\jruby-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\json-lib-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\http*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\log4j-over-slf4j-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\logback-classic-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\logback-core-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\mibble-parser-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\slf4j-api-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\snmp4j-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\spring-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\quartz-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\mybatis-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\hsqldb-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\struts2-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\xwork-core-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\c3p0-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\mchange-commons-java-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\mysql-connector-java-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\dubbo-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\javassist-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\zkclient-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\zookeeper-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\commons-lang-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\ezmorph-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\taobao-express-*.jar .\lib\ /E/Y
xcopy ..\..\WEB-INF\lib\topvision-*.jar .\lib\ /E/Y
