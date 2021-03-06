package ca.effpro.hadoop.mrbasics.old;

import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class LinkExtractMapper extends Mapper<LongWritable, Text, Text, Text>
{
  private static final Log logger = LogFactory.getLog(LinkExtractMapper.class);
  
  private Text outKey = new Text();
  private Text outValue = new Text();
  private org.apache.hadoop.conf.Configuration conf;
  
  public LinkExtractMapper() {}
  
  public void setup(Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
    conf = context.getConfiguration();
    logger.info("Set up invoked ...");
  }
 
  public void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
    throws IOException, InterruptedException
  {
    String rssFeedXMLString = value.toString().replaceAll("\r", "").replaceAll("\n", "");
    String[] splitsWithSuffixes = StringUtils.splitByWholeSeparator(rssFeedXMLString, "<link>");
    

    for (String splitWithSuffix : splitsWithSuffixes) {
      if (splitWithSuffix.indexOf("link") != -1)
      {
        if (splitWithSuffix.indexOf("region") != -1)
        {

          String link = splitWithSuffix.replaceAll("</link>.*", "");
          


          String regionKey = StringUtils.split(link, "/")[3];
          

          outValue.clear();
          outValue.set(link);
          
          outKey.clear();
          outKey.set(regionKey);
          
          context.write(outKey, outValue);
        }
      }
    }
  }
}