#!/bin/bash

MAIN_CLASS=ca.effpro.hadoop.data.TextToSequenceFileConvertor
INPUT_DIR=/user/it1/mrbasic/input/rss/kijiji/150502
OUTPUT_DIR=/user/it1/mrbasic/output/sequence

JAR=./mrbasic-1.0.jar
LIBJARS=.
LIBJARS=$LIBJARS,./commons-lang3-3.3.2.jar

echo "LIB JARS : $LIBJARS"

export HADOOP_CLASSPATH=`echo ${LIBJARS} | sed s/,/:/g`

CMD_OPTIONS='-D mapred.reduce.tasks=1'

hadoop jar $JAR $MAIN_CLASS -libjars $LIBJARS $CMD_OPTIONS KijijiMR $INPUT_DIR $OUTPUT_DIR compress
