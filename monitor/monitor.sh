#!/bin/sh
cd ..
JAVA='jre/bin/java'
OPTS=-Dorg.osgi.framework.bootdelegation=*
CP='conf;lib:lib/com.topvision.launcher-1.0.1.jar:lib/org.apache.felix.main-5.6.10.jar'
MAIN=com.topvision.launcher.TopvisionToolSets
echo =====================================================================
echo   Topvision Tool Sets Server Startup Environment
echo   JAVA: ${JAVA}
echo   JAVA_OPTS: ${OPTS}
echo   CLASSPATH: ${CP}
echo   CMD: ${JAVA} ${OPTS} -classpath ${CP} ${MAIN}
echo ================================================================
$JAVA $OPTS -classpath $CP $MAIN