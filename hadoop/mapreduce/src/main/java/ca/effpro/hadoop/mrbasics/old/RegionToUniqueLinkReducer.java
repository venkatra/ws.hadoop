package ca.effpro.hadoop.mrbasics.old;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class RegionToUniqueLinkReducer extends Reducer<Text, Text, Text, Text>
{
  private static final Log logger = LogFactory.getLog(RegionToUniqueLinkReducer.class);
  
  private org.apache.hadoop.conf.Configuration conf;
  private Set<String> setOfRegions = new HashSet();
  
  public RegionToUniqueLinkReducer() {}
  
  public void setup(Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException { conf = context.getConfiguration();
    logger.info("Set up invoked ...");
  }
  
  public void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
    throws IOException, InterruptedException
  {
    logger.info("reducer invoked ...");
    
    Set<String> setOfLinks = new HashSet();
    

    for (Text value : values) {
      String link = value.toString();
      
      if (!setOfLinks.contains(link))
      {

        setOfLinks.add(link);
        context.write(key, value);
        
        if (!setOfRegions.contains(key.toString()))
        {

          setOfRegions.add(key.toString());
          
          Counter counter = context.getCounter(KijijiMRCounterEnums.class
            .getName(), KijijiMRCounterEnums.UNIQUE_REGIONS
            .toString());
          counter.increment(1L);
        }
      }
    }
  }
}