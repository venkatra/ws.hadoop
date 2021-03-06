package ca.effpro.learn.hadoop.mr.tpt.exer1;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.Text;
import org.junit.Assert;
import org.junit.Test;

public class YearPartionerTest {

	private static final Log logger = LogFactory
			.getLog(YearPartionerTest.class);

	@Test
	public void test$getPartion() {
		
		//logger.info("OUT: " + StringUtils.strip("  2 0 0 8 0 2 0 5 ".trim().replaceAll(" ","")));
		
		//if(1==1) return;
		
		String[][] params = new String[][]{
			new String[] {"20080108","01","1","0"},
			new String[] {"20080108","01","2","0"},
			new String[] {"20090108","01","1","0"},
			new String[] {"20090108","01","2","1"},
		};
		
		
		YearMonthToInfractionWritable writable = new YearMonthToInfractionWritable();
		YearPartioner partioner = new YearPartioner();
		
		for(String[] param : params) {
			logger.info("=> " + StringUtils.join(param,","));
			
			writable.set(param[0], param[1]);
			
			int partion = partioner.getPartition(writable, new Text("XYX"), Integer.parseInt(param[2]));
			Assert.assertEquals(param[3], "" + partion);
			
		}
		
	}
}
