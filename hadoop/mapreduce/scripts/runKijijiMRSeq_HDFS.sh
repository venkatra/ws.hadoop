#!/bin/bash

MAIN_CLASS=ca.effpro.hadoop.kijiji.mr.simple.KijijiMRSeqInputOutputDriver
INPUT_DIR=/user/it1/mrbasic/output/sequence
OUTPUT_DIR=/user/it1/mrbasic/output/sequenceOut

JAR=./mrbasic-1.0.jar
LIBJARS=.
LIBJARS=$LIBJARS,./commons-lang3-3.3.2.jar

echo "LIB JARS : $LIBJARS"

export HADOOP_CLASSPATH=`echo ${LIBJARS} | sed s/,/:/g`

CMD_OPTIONS='-D mapred.reduce.tasks=1'

hadoop jar $JAR $MAIN_CLASS -libjars $LIBJARS $CMD_OPTIONS KijijiMR $INPUT_DIR $OUTPUT_DIR 
