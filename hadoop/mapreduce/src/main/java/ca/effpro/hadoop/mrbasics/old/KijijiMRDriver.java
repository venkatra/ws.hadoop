package ca.effpro.hadoop.mrbasics.old;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.ToolRunner;

public class KijijiMRDriver extends MRBasicDriverBase
{
  private static final Log logger = LogFactory.getLog(KijijiMRDriver.class);
  
  public KijijiMRDriver() {}
  
  public int run(String[] args) throws Exception { super.initArguments(args);
    

    Configuration conf = super.getConf();
    

    Job job = new Job(conf, jobName);
    job.setJarByClass(getClass());
    
    job.setMapperClass(LinkExtractMapper.class);
    job.setReducerClass(RegionToUniqueLinkReducer.class);
    

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    

    FileInputFormat.addInputPath(job, new Path(inputDir));
    job.setInputFormatClass(TextInputFormat.class);
    

    FileOutputFormat.setOutputPath(job, new Path(outputDir));
    job.setOutputFormatClass(TextOutputFormat.class);
    

    return job.waitForCompletion(true) ? 0 : 1;
  }
  
  public static void main(String[] args) throws Exception
  {
    int res = ToolRunner.run(new Configuration(), new KijijiMRDriver(), args);
    
    System.exit(res);
  }
}