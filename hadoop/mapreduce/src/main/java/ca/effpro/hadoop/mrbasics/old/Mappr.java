package ca.effpro.hadoop.mrbasics.old;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

public class Mappr extends Mapper<LongWritable, Text, Text, IntWritable> {
	private static final Log logger = LogFactory.getLog(Mappr.class);

	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();

	private boolean caseSensitive;
	private Set<String> patternsToSkip = new HashSet<String>();

	private Configuration conf;
	private BufferedReader fis;

	@Override
	public void setup(Context context) throws IOException, InterruptedException {
		conf = context.getConfiguration();
		logger.info("Set up invoked ...");
		/*
		//caseSensitive = conf.getBoolean("wordcount.case.sensitive", true);
		//if (conf.getBoolean("wordcount.skip.patterns", true)) {
			URI[] patternsURIs = Job.getInstance(conf).getCacheFiles();
			for (URI patternsURI : patternsURIs) {
				Path patternsPath = new Path(patternsURI.getPath());
				String patternsFileName = patternsPath.getName().toString();
				parseSkipFile(patternsFileName);
			}
		}
		*/
	}

	private void parseSkipFile(String fileName) {
		try {
			fis = new BufferedReader(new FileReader(fileName));
			String pattern = null;
			while ((pattern = fis.readLine()) != null) {
				patternsToSkip.add(pattern);
			}
		} catch (IOException ioe) {
			System.err
					.println("Caught exception while parsing the cached file '"
							+ StringUtils.stringifyException(ioe));
		}
	}

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		logger.info("map invoked ...");
		
		context.write(new Text("M"), one);
		
		Counter counter = context.getCounter(SimpleCounterEnums.class.getName(),
				SimpleCounterEnums.INPUT_WORDS.toString());
			counter.increment(1);
		
	}
}
