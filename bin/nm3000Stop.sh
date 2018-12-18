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

jre/bin/java -classpath "$CLASSPATH" com.topvision.console.JConsoleService stop
