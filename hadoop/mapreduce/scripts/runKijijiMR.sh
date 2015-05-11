#!/bin/bash

MAIN_CLASS=ca.effpro.hadoop.kijiji.mr.simple.KijijiMRDriver
INPUT_DIR=../hdp_standalone/data/input/kijijiRss.txt
OUTPUT_DIR=../hdp_standalone/data/output
JAR=target/mrbasic-1.0.jar

HADOOP_CMD='hadoop --config ../hdp_standalone/config'

. $HOME/Dev/setenv.sh

$HADOOP_CMD fs -rm -r -f $OUTPUT_DIR

MVN_REPO_HOME=/Users/d3vl0p3r/.m2/repository
LIBJARS=
LIBJARS=$MVN_REPO_HOME/org/apache/commons/commons-lang3/3.3.2/commons-lang3-3.3.2.jar
echo "LIB JARS : $LIBJARS"

export HADOOP_CLASSPATH=`echo ${LIBJARS} | sed s/,/:/g`

CMD_OPTIONS='-D mapred.reduce.tasks=1'

#LOCAL STANDALONE
$HADOOP_CMD  jar $JAR $MAIN_CLASS -libjars $LIBJARS $CMD_OPTIONS KijijiMR $INPUT_DIR $OUTPUT_DIR 