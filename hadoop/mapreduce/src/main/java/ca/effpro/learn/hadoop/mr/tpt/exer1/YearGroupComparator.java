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
		
		int cmp = w1.getYear().compareTo(w2.getYear());
		if( cmp != 0)
			return cmp;
		
		int cd1 = Integer.parseInt(w1.getInfractionCode().toString());
		int cd2 = Integer.parseInt(w2.getInfractionCode().toString());
		
		return Integer.compare(cd1, cd2);
	}

	
	
	

}
