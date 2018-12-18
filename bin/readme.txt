SnmpGet.exe 单个OID采集采集
SnmpWalk.exe 扫描OID下所有节点
SnmpSet.exe SNMP设置

eg）测试设备的SNMP是否可用
snmpget -r:192.168.1.1 -v:2c -c:"public" -o:.1.3.6.1.2.1.1.1.0
#注意：需要修改192.168.1.1为设备的正确IP和public为设备的正确Community
SNMP不可用的可能性：
1. IP不可达，可以ping测试一下
2. SNMP端口不是161，可以在snmpget参数中加-p:161来修改
3. SNMP Community错误，需要知道正确的community
4. SNMP未开启，需要开启设备的snmp
5. 网络延迟，需要设置更大的超时时间，-t:2000来修改
6. 防火墙屏蔽协议包，屏蔽端口
7. 其他……

通过SNMP提取设备的基本信息
1. 打开test设备Snmp.bat
2. 修改设备的IP地址HOST
3. 修改设备的SNMP版本号VERSION
4. 修改设备的访问Community
5. 修改设备的读写Community
6. 修改测试设备读写时设置sysLocation节点的值
7. 保存并运行test设备Snmp.bat
8. 生成IP对应的日志文件:snmp.ip.log
 