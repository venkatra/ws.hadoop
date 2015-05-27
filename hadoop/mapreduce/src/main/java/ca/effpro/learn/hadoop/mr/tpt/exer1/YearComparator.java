package ca.effpro.learn.hadoop.mr.tpt.exer1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.io.WritableUtils;

public class YearComparator extends WritableComparator {
	
	static {
		WritableComparator.define(YearMonthToInfractionWritable.class, new YearComparator());
	}
	
	private static final Log logger = LogFactory
			.getLog(YearComparator.class);

	private static final IntWritable.Comparator INT_COMPARATOR = new IntWritable.Comparator();
	
	public YearComparator() {
		super(YearMonthToInfractionWritable.class, false);
	}

	@Override
	public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2,
			int l2) {
		try {
			int year1 = WritableUtils.decodeVIntSize(b1[s1]) + readVInt(b1,s1);
			int year2 = WritableUtils.decodeVIntSize(b2[s2]) + readVInt(b2,s2);
			
			logger.info("Year 1 : " + year1 + " : " + new String(b1, s1, year1).charAt(0));
			return INT_COMPARATOR.compare(b1, s1, year1, b2, s2  ,year2);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return -100;
	}
	
	

}