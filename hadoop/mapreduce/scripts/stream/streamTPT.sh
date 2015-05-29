#!/bin/bash
#
# This script is related to parse the tpt sequence file (output of 
# transformZipToSequenceFileDriver.sh) and print to the out. It demonstrates the usage of hadoop stream
#
INPUT_PATH=/user/it1/data/input/seq/tpt/part*
OUTPUT_PATH=/user/it1/data/input/seq/stream

#
# Ensure the directories and script has read & execute permission, so that the hadoop
# job could run the same
# 
MAP_SCRIPT=/bin/cat
#REDUCE_SCRIPT=/home/it1/mapreduce.learn/streamreduce.sh


echo "Deleting existing $OUTPUT_DIR ..."
hadoop fs -rm -r -f -skipTrash $OUTPUT_PATH

#
# libraries
#
LIBJARS=./mapreduce-0.0.1-SNAPSHOT.jar
LIBJARS=$LIBJARS,./commons-lang3-3.3.2.jar
#LIBJARS=$LIBJARS,./poi-3.11.jar

export HADOOP_CLASSPATH=`echo ${LIBJARS} | sed s/,/:/g`

#
# The following options are used for compressing the output
#
CMD_OPTIONS="$CMD_OPTIONS -Dmapred.output.compress=true"
CMD_OPTIONS="$CMD_OPTIONS -Dmapred.output.compression.type=BLOCK"
CMD_OPTIONS="$CMD_OPTIONS -Dmapred.output.compression.codec=org.apache.hadoop.io.compress.GzipCodec"

echo "Running the stream job ..."
CMD_ARGS=""
CMD_ARGS="$CMD_ARGS -libjars $LIBJARS" 
CMD_ARGS="$CMD_ARGS $CMD_OPTIONS"
CMD_ARGS="$CMD_ARGS -input $INPUT_PATH -output $OUTPUT_PATH"

CMD_ARGS="$CMD_ARGS -inputformat org.apache.hadoop.mapred.SequenceFileInputFormat"

#CMD_ARGS="$CMD_ARGS -outputformat TextOutputFormat"
#CMD_ARGS="$CMD_ARGS -verbose"
CMD_ARGS="$CMD_ARGS -mapper $MAP_SCRIPT"
#CMD_ARGS="$CMD_ARGS -reducer $REDUCE_SCRIPT"
#CMD_ARGS="$CMD_ARGS -numReduceTasks 0"

# streamjob
STREAM_JOB_CMD="hadoop jar /usr/hdp/2.2.4.2-2/hadoop-mapreduce/hadoop-streaming.jar "
echo "${STREAM_JOB_CMD} ${CMD_ARGS}"

${STREAM_JOB_CMD} ${CMD_ARGS}


exit 0

Usage: $HADOOP_PREFIX/bin/hadoop jar hadoop-streaming.jar [options]
Options:
  -input          <path> DFS input file(s) for the Map step.
  -output         <path> DFS output directory for the Reduce step.
  -mapper         <cmd|JavaClassName> Optional. Command to be run as mapper.
  -combiner       <cmd|JavaClassName> Optional. Command to be run as combiner.
  -reducer        <cmd|JavaClassName> Optional. Command to be run as reducer.
  -file           <file> Optional. File/dir to be shipped in the Job jar file.
                  Deprecated. Use generic option "-files" instead.
  -inputformat    <TextInputFormat(default)|SequenceFileAsTextInputFormat|JavaClassName>
                  Optional. The input format class.
  -outputformat   <TextOutputFormat(default)|JavaClassName>
                  Optional. The output format class.
  -partitioner    <JavaClassName>  Optional. The partitioner class.
  -numReduceTasks <num> Optional. Number of reduce tasks.
  -inputreader    <spec> Optional. Input recordreader spec.
  -cmdenv         <n>=<v> Optional. Pass env.var to streaming commands.
  -mapdebug       <cmd> Optional. To run this script when a map task fails.
  -reducedebug    <cmd> Optional. To run this script when a reduce task fails.
  -io             <identifier> Optional. Format to use for input to and output
                  from mapper/reducer commands
  -lazyOutput     Optional. Lazily create Output.
  -background     Optional. Submit the job and don't wait till it completes.
  -verbose        Optional. Print verbose output.
  -info           Optional. Print detailed usage.
  -help           Optional. Print help message.

Generic options supported are
-conf <configuration file>     specify an application configuration file
-D <property=value>            use value for given property
-fs <local|namenode:port>      specify a namenode
-jt <local|resourcemanager:port>    specify a ResourceManager
-files <comma separated list of files>    specify comma separated files to be copied to the map reduce cluster
-libjars <comma separated list of jars>    specify comma separated jar files to include in the classpath.
-archives <comma separated list of archives>    specify comma separated archives to be unarchived on the compute machines.

The general command line syntax is
bin/hadoop command [genericOptions] [commandOptions]


Usage tips:
In -input: globbing on <path> is supported and can have multiple -input

Default Map input format: a line is a record in UTF-8 the key part ends at first
  TAB, the rest of the line is the value

To pass a Custom input format:
  -inputformat package.MyInputFormat

Similarly, to pass a custom output format:
  -outputformat package.MyOutputFormat

The files with extensions .class and .jar/.zip, specified for the -file
  argument[s], end up in "classes" and "lib" directories respectively inside
  the working directory when the mapper and reducer are run. All other files
  specified for the -file argument[s] end up in the working directory when the
  mapper and reducer are run. The location of this working directory is
  unspecified.

To set the number of reduce tasks (num. of output files) as, say 10:
  Use -numReduceTasks 10
To skip the sort/combine/shuffle/sort/reduce step:
  Use -numReduceTasks 0
  Map output then becomes a 'side-effect output' rather than a reduce input.
  This speeds up processing. This also feels more like "in-place" processing
  because the input filename and the map input order are preserved.
  This is equivalent to -reducer NONE

To speed up the last maps:
  -D mapreduce.map.speculative=true
To speed up the last reduces:
  -D mapreduce.reduce.speculative=true
To name the job (appears in the JobTracker Web UI):
  -D mapreduce.job.name='My Job'
To change the local temp directory:
  -D dfs.data.dir=/tmp/dfs
  -D stream.tmpdir=/tmp/streaming
Additional local temp directories with -jt local:
  -D mapreduce.cluster.local.dir=/tmp/local
  -D mapreduce.jobtracker.system.dir=/tmp/system
  -D mapreduce.cluster.temp.dir=/tmp/temp
To treat tasks with non-zero exit status as SUCCEDED:
  -D stream.non.zero.exit.is.failure=false
Use a custom hadoop streaming build along with standard hadoop install:
  $HADOOP_PREFIX/bin/hadoop jar /path/my-hadoop-streaming.jar [...]\
    [...] -D stream.shipped.hadoopstreaming=/path/my-hadoop-streaming.jar
For more details about jobconf parameters see:
  http://wiki.apache.org/hadoop/JobConfFile
To set an environement variable in a streaming command:
   -cmdenv EXAMPLE_DIR=/home/example/dictionaries/

Shortcut:
   setenv HSTREAMING "$HADOOP_PREFIX/bin/hadoop jar hadoop-streaming.jar"

Example: $HSTREAMING -mapper "/usr/local/bin/perl5 filter.pl"
           -file /local/filter.pl -input "/logs/0604*/*" [...]
  Ships a script, invokes the non-shipped perl interpreter. Shipped files go to
  the working directory so filter.pl is found by perl. Input files are all the
  daily logs for days in month 2006-04
[it1@sandbox mapreduce.learn]$ 
