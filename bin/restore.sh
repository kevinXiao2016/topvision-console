#!/bin/bash

# Program: restore.sh
# History: 2016-8-2 Rod Johnson First release

# Check User Authority
if [ `id -u` -ne 0 ];then
echo "INSTALL ERROR: You must use root to restore NM3000"
exit 0
fi

## Check Linux Release
LINUXRELEASE=0
if [ -f /etc/redhat-release ]; then
  linux_release_redhat=$(cat /etc/redhat-release|awk '{print $1$2}'|grep 'RedHat')
  linux_release_centos=$(cat /etc/redhat-release|awk '{print $1}'|grep CentOS)
  if [ ! -z $linux_release_redhat ]; then
    LINUXRELEASE='RedHat'
  fi
  echo $linux_release_centos
  if [ ! -z $linux_release_centos ]; then
    LINUXRELEASE='CentOS'
  fi
else
  linux_release_ubuntu=$(cat /etc/issue|sed -n '1p'|awk '{print $1}'|grep Ubuntu)
  if [ ! -z $linux_release_ubuntu ]; then
    LINUXRELEASE='Ubuntu'
  fi
fi

if [ $LINUXRELEASE = 0 ]; then
  echo "INSTALL ERROR: Unknown Linux Release"
  exit 0
fi


# Find Install Path
NM3000INSTALLPATH=$(ls -al /etc/init.d/nm3000_server|awk -F "->" '{print $2}'|awk -F "/" '{print "/"$2"/"$3"/"$4}')

echo $NM3000INSTALLPATH/backup;

if [ ! -e $NM3000INSTALLPATH/backup ]; then
   echo "RESTORE ERROR: Backup File not exists"
   exit 0
fi

service nm3000_server stop

# Stop Server Process
sleep 10
serverProcess=$(ps -ef|grep jre/bin/java|grep topvision|grep -v grep|awk '{print $2}')
if [ "$serverProcess" != "" ]; then
  kill -9 $serverProcess
fi


# cd $NM3000INSTALLPATH/mysql/bin
# ./mysql.exe --defaults-file="../my.cnf" -h127.0.0.1 -P3003 -uroot -pems < ../../webapp/META-INF/script/rollback-lasted.sql


cd $NM3000INSTALLPATH/backup
mkdir webapp/WEB-INF/classes/com/topvision/ems/databaserollback
cp -f ../webapp/META-INF/script/rollback-lasted.sql webapp/WEB-INF/classes/com/topvision/ems/databaserollback/database-data-mysql.sql


cd $NM3000INSTALLPATH
rm -rf webapp
rm -rf bin
rm -rf lib
rm -rf jre
rm -rf tmp

mv backup/bin ./
mv backup/lib ./
mv backup/jre ./
mv backup/webapp ./

# rm -rf backup

# Start NM3000 Server
service nm3000_server start




 



















