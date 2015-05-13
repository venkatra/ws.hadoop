package ca.effpro.learn.hadoop.mr.format.zip;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Used for logging file names (in the zip file) entries.
 * 
 * Input Key : filename (from zip file)
 * Input Value : file content
 * Output Key : filename (from zip file)
 * Output Value : size of file content
 *
 */
public class ZipFileMapper extends Mapper<Text, BytesWritable, Text, IntWritable> {
	private static final Log logger = LogFactory.getLog(ZipFileMapper.class);

	private Text outKey = new Text();
	private IntWritable outValue = new IntWritable();
	private org.apache.hadoop.conf.Configuration conf;

	public void setup(Mapper<Text, BytesWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		conf = context.getConfiguration();
		//logger.info("Set up invoked ...");
	}

	public void map(Text key, BytesWritable value,
			Mapper<Text, BytesWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {

		logger.info("Zip file entry : " + key.toString());
		
		outValue.set(value.getLength());
         
         outKey.clear();
         outKey.set(key);
         
         context.write(outKey, outValue);
	}
}