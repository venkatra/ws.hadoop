#!/bin/bash
#
# This script is related to driver code : ca.effpro.learn.hadoop.mr.tpt.ProcessExcelFileDriver
# 
# The script demonstrates :
#
# -> the invocation of driver program and thus submitting the job to hadoop
#
#

MAIN_CLASS=ca.effpro.learn.hadoop.mr.tpt.ProcessExcelFileDriver
INPUT_DIR=/user/it1/data/input/raw/tpt/parking_tickets_readme.xls
OUTPUT_DIR=/user/it1/data/input/seq/tpt/xls.temp

JAR=./mapreduce-0.0.1-SNAPSHOT.jar

#
# Third party libraries
#
LIBJARS=.
LIBJARS=$LIBJARS,./commons-lang3-3.3.2.jar
LIBJARS=$LIBJARS,./poi-3.11.jar

export HADOOP_CLASSPATH=`echo ${LIBJARS} | sed s/,/:/g`

echo "Deleting existing $OUTPUT_DIR ..."
hadoop fs -rm -r -f -skipTrash $OUTPUT_DIR

#
# command line options
#
CMD_OPTIONS=''

echo "Running the map reduce ..."
echo "Command options : $CMD_OPTIONS"
hadoop jar $JAR $MAIN_CLASS -libjars $LIBJARS $CMD_OPTIONS $INPUT_DIR $OUTPUT_DIR 

echo "Output"
hadoop fs -cat $OUTPUT_DIR/part*