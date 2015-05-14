package ca.effpro.learn.hadoop.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.ToolRunner;

import ca.effpro.learn.hadoop.mr.format.zip.ZipFileEntriesToContentMapper;
import ca.effpro.learn.hadoop.mr.format.zip.ZipFileInputFormat;

public class TextToSequenceFileConvertor extends MRBase {

	public int run(String[] args) throws Exception {
		
		if(super.parserArguments(args) < 1)
			return -1;
		
		Job job = new Job(getConf(), "Create Sequence File, from text file");
		job.setJarByClass(getClass());
		
		ZipFileInputFormat.addInputPath(job, new Path(getInputFileDir()));
		SequenceFileOutputFormat.setOutputPath(job, new Path(getOutputFileDir()));
		    
		job.setMapperClass(ZipFileEntriesToContentMapper.class);
		//job.setMapOutputKeyClass(Text.class);
		//job.setMapOutputValueClass(BytesWritable.class);
		
		//job.setReducerClass(ZipFileEntryToContentReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		ZipFileInputFormat.setLenient( true );
		
		job.setInputFormatClass(ZipFileInputFormat.class);
		job.setOutputFormatClass( SequenceFileOutputFormat.class);
		
		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new Configuration(),
				new TextToSequenceFileConvertor(), args);
		System.exit(exitCode);
	}
}