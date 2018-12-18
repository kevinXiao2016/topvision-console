#!/bin/bash

cd nm3000Path/ems/webapp/META-INF

engineInstall=$(ls|grep engine)
if [ "$engineInstall" != "" ]; then
   rm -rf engine
fi

mkdir engine
mkdir engine/conf
mkdir engine/dll
mkdir engine/mibs
mkdir engine/lib
mkdir engine/bin
cp ../../bin/runEngine.* engine/
cp ../../bin/diskspace engine/bin/
chmod +x engine/bin/diskspace
cp ../WEB-INF/conf/engine.properties engine/conf/
cp ../WEB-INF/conf/quartz.properties engine/conf/
cp ../WEB-INF/conf/logback.xml engine/conf/

cp ../WEB-INF/dll/* engine/dll/
cp ../WEB-INF/mibs/* engine/mibs/

cp ../WEB-INF/lib/aopalliance-*.jar engine/lib/
cp ../WEB-INF/lib/commons-logging-*.jar engine/lib/
cp ../WEB-INF/lib/com.topvision.*.jar engine/lib/
cp ../WEB-INF/lib/jruby-*.jar engine/lib/
cp ../WEB-INF/lib/json-lib-*.jar engine/lib/
cp ../WEB-INF/lib/http*.jar engine/lib/
cp ../WEB-INF/lib/log4j-over-slf4j-*.jar engine/lib/
cp ../WEB-INF/lib/logback-classic-*.jar engine/lib/
cp ../WEB-INF/lib/logback-core-*.jar engine/lib/
cp ../WEB-INF/lib/mibble-parser-*.jar engine/lib/
cp ../WEB-INF/lib/slf4j-api-*.jar engine/lib/
cp ../WEB-INF/lib/snmp4j-*.jar engine/lib/
cp ../WEB-INF/lib/spring-*.jar engine/lib/
cp ../WEB-INF/lib/quartz-*.jar engine/lib/
cp ../WEB-INF/lib/mybatis-*.jar engine/lib/
cp ../WEB-INF/lib/hsqldb-*.jar engine/lib/
cp ../WEB-INF/lib/struts2-*.jar engine/lib/
cp ../WEB-INF/lib/xwork-core-*.jar engine/lib/
cp ../WEB-INF/lib/c3p0-*.jar engine/lib/
cp ../WEB-INF/lib/mchange-commons-java-*.jar engine/lib/
cp ../WEB-INF/lib/mysql-connector-java-*.jar engine/lib/
cp ../WEB-INF/lib/dubbo-*.jar engine/lib/
cp ../WEB-INF/lib/javassist-*.jar engine/lib/
cp ../WEB-INF/lib/zkclient-*.jar engine/lib/
cp ../WEB-INF/lib/zookeeper-*.jar engine/lib/
cp ../WEB-INF/lib/commons-lang-*.jar engine/lib/
cp ../WEB-INF/lib/ezmorph-*.jar engine/lib/
cp ../WEB-INF/lib/taobao-express-*.jar engine/lib/
cp ../WEB-INF/lib/topvision-*.jar engine/lib/


