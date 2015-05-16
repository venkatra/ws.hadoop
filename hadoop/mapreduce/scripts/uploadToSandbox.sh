#!/bin/bash
#
# This script is usd to upload the various artifacts to the hadoop sandbox.
#

FLZ=target/mapreduce-0.0.1-SNAPSHOT.jar
FLZ="$FLZ ./scripts/transformZipToSequenceFileDriver.sh"
FLZ="$FLZ scripts/processExcelFileDriver.sh"

#FLZ="$FLZ $HOME/.m2/repository/org/apache/poi/poi/3.11/poi-3.11.jar"

scp -P 2222 $FLZ it1@localhost:./mapreduce.learn