SnmpGet.exe ����OID�ɼ��ɼ�
SnmpWalk.exe ɨ��OID�����нڵ�
SnmpSet.exe SNMP����

eg�������豸��SNMP�Ƿ����
snmpget -r:192.168.1.1 -v:2c -c:"public" -o:.1.3.6.1.2.1.1.1.0
#ע�⣺��Ҫ�޸�192.168.1.1Ϊ�豸����ȷIP��publicΪ�豸����ȷCommunity
SNMP�����õĿ����ԣ�
1. IP���ɴ����ping����һ��
2. SNMP�˿ڲ���161��������snmpget�����м�-p:161���޸�
3. SNMP Community������Ҫ֪����ȷ��community
4. SNMPδ��������Ҫ�����豸��snmp
5. �����ӳ٣���Ҫ���ø���ĳ�ʱʱ�䣬-t:2000���޸�
6. ����ǽ����Э��������ζ˿�
7. ��������

ͨ��SNMP��ȡ�豸�Ļ�����Ϣ
1. ��test�豸Snmp.bat
2. �޸��豸��IP��ַHOST
3. �޸��豸��SNMP�汾��VERSION
4. �޸��豸�ķ���Community
5. �޸��豸�Ķ�дCommunity
6. �޸Ĳ����豸��дʱ����sysLocation�ڵ��ֵ
7. ���沢����test�豸Snmp.bat
8. ����IP��Ӧ����־�ļ�:snmp.ip.log
 