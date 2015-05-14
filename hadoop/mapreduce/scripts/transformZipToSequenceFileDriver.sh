#!/bin/bash
#
# This script is related to driver code : ca.effpro.learn.hadoop.mr.tpt.TransformZipToSequenceFileDriver
# 
# The script demonstrates :
#
# -> the invocation of driver program and thus submitting the job to hadoop
#
# -> the option to set the number of reducers
#
# -> the option to set compression
#

MAIN_CLASS=ca.effpro.learn.hadoop.mr.tpt.TransformZipToSequenceFileDriver
INPUT_DIR=/user/it1/data/input/raw/tpt/parking_tickets_2008.zip
OUTPUT_DIR=/user/it1/data/input/seq/tpt

JAR=./mapreduce-0.0.1-SNAPSHOT.jar

#
# Third party libraries
#
LIBJARS=.
LIBJARS=$LIBJARS,./commons-lang3-3.3.2.jar

export HADOOP_CLASSPATH=`echo ${LIBJARS} | sed s/,/:/g`

echo "Deleting existing $OUTPUT_DIR ..."
hadoop fs -rm -r -f -skipTrash $OUTPUT_DIR

#
# The number of reducers
#
CMD_OPTIONS='-D mapred.reduce.tasks=1'

#
# The following options are used for compressing the output 
#
CMD_OPTIONS="$CMD_OPTIONS -Dmapred.output.compress=true"
CMD_OPTIONS="$CMD_OPTIONS -Dmapred.output.compression.type=BLOCK"
CMD_OPTIONS="$CMD_OPTIONS -Dmapred.output.compression.codec=org.apache.hadoop.io.compress.GzipCodec"


echo "Running the map reduce ..."
echo "Command options : $CMD_OPTIONS"
hadoop jar $JAR $MAIN_CLASS -libjars $LIBJARS $CMD_OPTIONS $INPUT_DIR $OUTPUT_DIR 
