package ca.effpro.learn.hadoop.mr.tpt.exer1;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.junit.Assert;
import org.junit.Test;

public class YearMonthToInfractionWritableTest {
	private static final Log logger = LogFactory
			.getLog(YearMonthToInfractionWritableTest.class);

	@Test
	public void testCompareTo() {
		
		String[][] params = new String[][]{
			new String[] {"20080108","01","20080108","01","0"},
			new String[] {"20080108","01","20080109","01","0"},
			new String[] {"20080108","01","20080107","01","0"},
			
			new String[] {"20080108","01","20080108","02","-1"},
			new String[] {"20080108","02","20080108","01","1"},
			
			new String[] {"20080108","01","20090108","01","-1"},
			new String[] {"20080108","01","20070108","01","1"},
			
			new String[] {"20080108","01","20080208","01","-1"},
			new String[] {"20081208","01","20090108","01","-1"},
			new String[] {"20080108","01","20070108","01","1"},
			new String[] {"20080208","01","20080108","01","1"},
		};
		
		
		YearMonthToInfractionWritable writable = new YearMonthToInfractionWritable();
		YearMonthToInfractionWritable writable$2 = new YearMonthToInfractionWritable();
		
		for(String[] param : params) {
			logger.info("=> " + StringUtils.join(param,","));
			
			writable.set(param[0], param[1]);
			
			writable$2.set(new IntWritable(Integer.parseInt(param[2].substring(0, 4)))
			,new IntWritable(Integer.parseInt(param[2].substring(5, 6)))
			,new Text(param[3]));
			
			Assert.assertEquals(param[4], "" + writable.compareTo(writable$2));
			
		}
		
	}

}
