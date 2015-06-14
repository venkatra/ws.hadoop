#!/bin/bash
#
# This script is used to upload the various artifacts to the hadoop sandbox.
#

FLZ=
FLZ="$FLZ scripts/processTPTAvroFile.pig"
FLZ="$FLZ ../avro/src/main/java/ca/effpro/learn/avro/tpt/parkingTicket.avsc"

scp -P 2222 $FLZ it1@localhost:./mapreduce.learn
