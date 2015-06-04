package ca.effpro.learn.hadoop.mr.tpt.exer1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileAsTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.ToolRunner;

import ca.effpro.learn.hadoop.mr.MRBase;

public class YearlyAmountCollectedToInfractionDriver extends MRBase {

	public int run(String[] args) throws Exception {
		
		if(super.parserArguments(args) < 1)
			return -1;
		
		@SuppressWarnings("deprecation")
		Job job = new Job(getConf(), "Compute yearly amount collected by infraction");
		job.setJarByClass(getClass());
		
		SequenceFileAsTextInputFormat.setInputPaths(job, new Path(getInputFileDir()));
		TextOutputFormat.setOutputPath(job, new Path(getOutputFileDir()));
		job.setInputFormatClass( SequenceFileAsTextInputFormat.class );
		job.setOutputFormatClass( TextOutputFormat.class );
		
		job.setMapperClass(YearMonthInfractionMapper.class);
		job.setMapOutputKeyClass(YearMonthToInfractionWritable.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setReducerClass(YearlyCollectionOfAmountToInfractionReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
//		job.setNumReduceTasks(1);
		
		job.setGroupingComparatorClass(YearGroupComparator.class);
		job.setPartitionerClass(YearPartioner.class);
		job.setSortComparatorClass(YearGroupComparator.class);
		
		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new Configuration(),
				new YearlyAmountCollectedToInfractionDriver(), args);
		System.exit(exitCode);
	}
}