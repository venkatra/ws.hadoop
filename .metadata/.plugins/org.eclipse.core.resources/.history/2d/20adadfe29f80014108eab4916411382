#!/bin/bash

MAIN_CLASS=ca.effpro.learn.hadoop.mr.TextToSequenceFileConvertor
INPUT_DIR=/user/it1/data/input/raw/tpt/parking_tickets_2008.zip
OUTPUT_DIR=/user/it1/data/input/seq/tpt

JAR=./mapreduce-0.0.1-SNAPSHOT.jar
LIBJARS=.
LIBJARS=$LIBJARS,./commons-lang3-3.3.2.jar

echo "LIB JARS : $LIBJARS"

export HADOOP_CLASSPATH=`echo ${LIBJARS} | sed s/,/:/g`

CMD_OPTIONS='-D mapred.reduce.tasks=1'

hadoop jar $JAR $MAIN_CLASS -libjars $LIBJARS $CMD_OPTIONS $INPUT_DIR $OUTPUT_DIR 
