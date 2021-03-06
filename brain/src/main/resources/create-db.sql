CREATE SCHEMA IF NOT EXISTS JOBSSCHEMA;
SET SCHEMA JOBSSCHEMA;
CREATE TABLE IF NOT EXISTS scheduledjobs(id int primary key auto_increment, jobidentifier varchar(64), jobName varchar(255), cronSchedule varchar(255), topic varchar(255), payload varchar(1024), UNIQUE KEY jobName_Unique (jobName));
CREATE TABLE IF NOT EXISTS powermon(id int primary key auto_increment, reading double , readat datetime);
CREATE TABLE IF NOT EXISTS mqttmon(id int primary key auto_increment, topicname varchar(255), payload varchar(512), created datetime);
