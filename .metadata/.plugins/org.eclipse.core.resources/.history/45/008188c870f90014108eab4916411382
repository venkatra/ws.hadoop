package ca.effpro.hadoop.mrbasics.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class Redr extends Reducer<Text, IntWritable, Text, IntWritable> {
	private IntWritable result = new IntWritable();

	private static final Log logger = LogFactory.getLog(Mappr.class);

	static enum CountersEnum {
		OUTPUT_WORDS
	}

	private final static IntWritable one = new IntWritable(1);
	private Configuration conf;

	@Override
	public void setup(Context context) throws IOException, InterruptedException {
		conf = context.getConfiguration();
		logger.info("Set up invoked ...");
	}

	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {

		logger.info("reducer invoked ...");

		context.write(new Text("M"), one);

		Counter counter = context.getCounter(
				SimpleCounterEnums.class.getName(),
				SimpleCounterEnums.REDUCER_WORDS.toString());
		counter.increment(1);

		context.write(key, one);
	}
}