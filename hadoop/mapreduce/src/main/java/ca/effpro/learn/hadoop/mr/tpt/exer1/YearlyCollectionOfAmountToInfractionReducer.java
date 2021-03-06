package ca.effpro.learn.hadoop.mr.tpt.exer1;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class YearlyCollectionOfAmountToInfractionReducer extends
		Reducer<YearMonthToInfractionWritable, Text, Text, IntWritable> {
	private static final Log logger = LogFactory
			.getLog(YearlyCollectionOfAmountToInfractionReducer.class);

	Text k4 = new Text();
	IntWritable v4 = new IntWritable();
	
	@SuppressWarnings("unchecked")
	public void reduce(
			YearMonthToInfractionWritable key,
			Iterable<Text> values,
			Reducer<YearMonthToInfractionWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		int totalAmount = 0;
		String[] valsConsituent;
		
		for (Text value : values) {
			valsConsituent = value.toString().split(",");
			totalAmount += Integer.parseInt(valsConsituent[0]);
		}
		
		int year = key.getYear().get();
		String infractionCode = key.getInfractionCode().toString();
		logger.info("+>" + year + ":" + infractionCode);
		
		k4.set(StringUtils.join(year ,"," ,infractionCode).trim());
		v4.set(totalAmount);
		
		context.write(k4, v4);

	}
}