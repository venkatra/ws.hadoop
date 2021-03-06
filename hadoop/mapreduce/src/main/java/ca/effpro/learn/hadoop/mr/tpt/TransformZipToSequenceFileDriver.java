package ca.effpro.learn.hadoop.mr.tpt;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.ToolRunner;

import ca.effpro.learn.hadoop.mr.MRBase;
import ca.effpro.learn.hadoop.mr.format.zip.ZipFileEntriesToContentMapper;
import ca.effpro.learn.hadoop.mr.format.zip.ZipFileInputFormat;

/**
 * The parking ticket dataset are downloaded in zip'd format (ex:parking_tickets_2008.zip). These would
 * then be ingested into HDFS (/user/it1/data/input/raw/tpt), without extracting.
 *  
 * This driver program demonstrates
 *  <li> A custom mapper, input format, record reader that reads the zip file 
 *  <li> Store the read data into a sequence file
 *  
 * The associated script (transformZipToSequenceFileDriver.sh) is used to invoke this driver and submit
 * the program in HDFS. The output would be stored in (/user/it1/data/input/seq/tpt). The script also
 * demonstartes setting options for compressing the output.
 *
 */
public class TransformZipToSequenceFileDriver extends MRBase {

	public int run(String[] args) throws Exception {
		
		if(super.parserArguments(args) < 1)
			return -1;
		
		Job job = new Job(getConf(), "Transform tpt zip to seq file");
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
				new TransformZipToSequenceFileDriver(), args);
		System.exit(exitCode);
	}
}