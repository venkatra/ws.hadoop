package ca.effpro.hadoop.mrbasics.old;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile.CompressionType;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.ToolRunner;

public class TextToSequenceFileConvertor extends MRBasicDriverBase
{
  private static final Log logger = LogFactory.getLog(TextToSequenceFileConvertor.class);
  
  public TextToSequenceFileConvertor() {}
  
  public int run(String[] args) throws Exception { super.initArguments(args);
    

    Configuration conf = super.getConf();
    

    Job job = new Job(conf, jobName);
    job.setJarByClass(getClass());
    
    job.setMapperClass(Mapper.class);
    job.setReducerClass(Reducer.class);
    
    job.setNumReduceTasks(0);
    
    job.setOutputKeyClass(LongWritable.class);
    job.setOutputValueClass(Text.class);
    

    TextInputFormat.addInputPath(job, new Path(inputDir));
    job.setInputFormatClass(TextInputFormat.class);
    

    SequenceFileOutputFormat.setOutputPath(job, new Path(outputDir));
    job.setOutputFormatClass(SequenceFileOutputFormat.class);
    

    if (args.length > 3) {
      logger.info("Compressing the output ...");
      SequenceFileOutputFormat.setCompressOutput(job, true);
      SequenceFileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);
 //     SequenceFileOutputFormat.setOutputCompressionType(job, SequenceFile.CompressionType.BLOCK);
    }
    

    return job.waitForCompletion(true) ? 0 : 1;
  }
  
  public static void main(String[] args) throws Exception
  {
    int res = ToolRunner.run(new Configuration(), new TextToSequenceFileConvertor(), args);
    
    System.exit(res);
  }
}