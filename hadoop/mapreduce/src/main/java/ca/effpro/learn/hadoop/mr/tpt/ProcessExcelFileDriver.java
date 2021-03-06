package ca.effpro.learn.hadoop.mr.tpt;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.ToolRunner;

import ca.effpro.learn.hadoop.mr.MRBase;
import ca.effpro.learn.hadoop.mr.format.xls.ExcelInputFormat;

/**
 * This driver program is to demonstrate the reading of excel file stored in HDFS. It uses a custom InputFormat
 * and a custom record reader.
 * 
 * The sample file (parking_tickets_readme.xls) is part of the toronto parking ticket dataset. The pre-requisite
 * is that the file would be stored in HDFS at /user/it1/data/input/raw/tpt
 *  
 * This driver program demonstrates
 *  <li> A custom mapper, input format, record reader that reads the excel file 
 *  <li> Print out using the default reducer
 *  
 * The associated script (processExcelFileDriver.sh) is used to invoke this driver and submit
 * the program in HDFS. The output would be printed using the default reducer.
 *
 * The program was based upon the blog : 
 *  https://sreejithrpillai.wordpress.com/2014/11/06/excel-inputformat-for-hadoop-mapreduce/
 */
public class ProcessExcelFileDriver extends MRBase {

	public int run(String[] args) throws Exception {
		
		if(super.parserArguments(args) < 1)
			return -1;
		
		Job job = new Job(getConf(), "Process excel file in hdfs");
		job.setJarByClass(getClass());
		
		ExcelInputFormat.addInputPath(job, new Path(getInputFileDir()));
		SequenceFileOutputFormat.setOutputPath(job, new Path(getOutputFileDir()));
		    
		job.setMapperClass(Mapper.class);
		//job.setMapOutputKeyClass(Text.class);
		//job.setMapOutputValueClass(BytesWritable.class);
		
		//job.setReducerClass(ZipFileEntryToContentReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setNumReduceTasks(0);
		
		job.setInputFormatClass(ExcelInputFormat.class);
		job.setOutputFormatClass( TextOutputFormat.class);
		
		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new Configuration(),
				new ProcessExcelFileDriver(), args);
		System.exit(exitCode);
	}
}