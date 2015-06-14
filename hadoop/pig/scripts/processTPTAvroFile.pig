
--
-- Tutorial blog : 
-- http://www.michael-noll.com/blog/2013/07/04/using-avro-in-mapreduce-jobs-with-hadoop-pig-hive/#pig
--

REGISTER /usr/hdp/current/pig-client/lib/piggybank.jar;
REGISTER /usr/hdp/current/pig-client/lib/avro-*.jar;
REGISTER /usr/hdp/current/pig-client/lib/jackson-core-asl-*.jar;
REGISTER /usr/hdp/current/pig-client/lib/jackson-mapper-asl-*.jar;
REGISTER /usr/hdp/current/pig-client/lib/json-simple-*.jar;
REGISTER /usr/hdp/current/pig-client/lib/snappy-java-*.jar;
REGISTER /usr/hdp/current/pig-client/lib/jython-standalone-*.jar;
		
records = LOAD '/user/it1/data/input/tpt/avro/part-m-00000'	
			USING org.apache.pig.piggybank.storage.avro.AvroStorage('no_schema_check',	
            	'schema_file', 'hdfs:///user/it1/data/input/tpt/parkingTicket.avsc');

frecords = limit records 5;
dump frecords;