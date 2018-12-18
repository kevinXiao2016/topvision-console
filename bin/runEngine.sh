#!/bin/bash

# PATH = ems/enginemgr/bin/engine-port  
JAVA=../../../jre/bin/java

OPTS="-Xms1024M -Xmx8192M"
CONFIG=".:$lib/*.jar"

#_jar=`ls lib | grep "..*\.jar$"`  #列举lib目录下的 *.jar 文件
#_classpath="${_jar} ${_zip}"
#classpath=`echo ${_classpath} | sed -e 's/ /:lib\//g'`   #包名前都要带个 lib/

CLASSPATH=`find lib -name *.jar|xargs|sed "s/ /:/g"`
CLASSPATH="./conf:"$CLASSPATH
echo $CLASSPATH

MAIN=com.topvision.ems.engine.launcher.Collector
 
$JAVA $OPTS -cp $CLASSPATH $MAIN& start

