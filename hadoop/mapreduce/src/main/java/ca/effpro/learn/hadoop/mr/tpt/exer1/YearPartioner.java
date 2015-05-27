package ca.effpro.learn.hadoop.mr.tpt.exer1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * partion will be based on year.
 * 
 * @author d3vl0p3r
 *
 */
public class YearPartioner extends Partitioner<YearMonthToInfractionWritable, Text>  {

	@Override
	public int getPartition(YearMonthToInfractionWritable key, Text val,
			int numOfReducers) {
		return key.getYear().get() % numOfReducers;
	}
	
}