CREATE SCHEMA IF NOT EXISTS JOBSSCHEMA;
SET SCHEMA JOBSSCHEMA;
CREATE TABLE IF NOT EXISTS scheduledjobs(id int primary key auto_increment, jobidentifier varchar(64), jobName varchar(255), cronSchedule varchar(255), topic varchar(255), payload varchar(1024), UNIQUE KEY jobName_Unique (jobName));
