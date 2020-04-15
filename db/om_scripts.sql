create tablespace om datafile 'E:\ORACLE\ORACLEXE\APP\ORACLE\ORADATA\XE\OM01.DBF' size 10m autoextend on next 5m;

create user om identified by om default tablespace om temporary tablespace temp quota unlimited on om;

grant dba to om;