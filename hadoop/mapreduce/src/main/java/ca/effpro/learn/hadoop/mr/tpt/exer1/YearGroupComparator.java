package ca.effpro.learn.hadoop.mr.tpt.exer1;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.io.WritableUtils;

public class YearGroupComparator extends WritableComparator {
	
	public YearGroupComparator() {
		super(YearMonthToInfractionWritable.class, true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		YearMonthToInfractionWritable w1 = (YearMonthToInfractionWritable)a;
		YearMonthToInfractionWritable w2  = (YearMonthToInfractionWritable)b;
		
		return w1.getYear().compareTo(w2.getYear());
	}

	
	
	

}