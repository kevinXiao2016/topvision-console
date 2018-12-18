#!/bin/sh

cd ..

CLASSPATH=${PWD}"/conf"
for file in $(ls ./lib/ | awk '{print $1}')
do
    CLASSPATH=${CLASSPATH}":"${PWD}"/lib/"${file}
done
for file in $(ls ./lib/jetty | awk '{print $1}')
do
    CLASSPATH=${CLASSPATH}":"${PWD}"/lib/jetty/"${file}
done
CLASSPATH=${CLASSPATH}":"

# stop nm3000 service
jre/bin/java -classpath "$CLASSPATH" com.topvision.console.JConsoleService stop
# start nm3000 service
jre/bin/java -Xms2G -Xmx6G -XX:-OmitStackTraceInFastThrow -XX:NewSize=256m -XX:MaxNewSize=512m -classpath "$CLASSPATH" com.topvision.console.JConsoleService&
