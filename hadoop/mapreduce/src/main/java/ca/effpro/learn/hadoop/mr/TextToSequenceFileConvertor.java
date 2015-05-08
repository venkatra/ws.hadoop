package ca.effpro.learn.hadoop.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.ToolRunner;

public class TextToSequenceFileConvertor extends MRBase {

	public int run(String[] args) throws Exception {
		
		if(super.parserArguments(args) < 1)
			return -1;
		
		Job job = new Job(getConf());
		job.setJarByClass(getClass());
		job.setJobName("Create Sequence File, from text file");

		FileInputFormat.setInputPaths(job, new Path(getInputFileDir()));
		FileOutputFormat.setOutputPath(job, new Path(getOutputFileDir()));

		job.setMapperClass(Mapper.class);
		//job.setOutputFormatClass(SequenceFileOutputFormat.class);
		job.setOutputFormatClass( TextOutputFormat.class);
		
		job.setNumReduceTasks(0);

		boolean success = job.waitForCompletion(true);
		return success ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new Configuration(),
				new TextToSequenceFileConvertor(), args);
		System.exit(exitCode);
	}
}