package ca.effpro.learn.hadoop.mr.tpt.exer1;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

public class YearGroupComparatorTest {
	private static final Log logger = LogFactory
			.getLog(YearGroupComparatorTest.class);

	@Test
	public void test$Compare() {

		String[][] params = new String[][] {
				new String[] { "20080108", "01", "20080108", "0" },
				new String[] { "20080108", "01", "20080208", "0" },
				new String[] { "20080108", "01", "20090108", "-1" },
				new String[] { "20080108", "01", "20070108", "1" },
				new String[] { "20080108", "01", "20060108", "2" }, };

		YearMonthToInfractionWritable w1 = new YearMonthToInfractionWritable();
		YearMonthToInfractionWritable w2 = new YearMonthToInfractionWritable();
		YearGroupComparator groupComparator = new YearGroupComparator();

		for (String[] param : params) {
			logger.info("=> " + StringUtils.join(param, ","));

			w1.set(param[0], param[1]);
			w2.set(param[2], param[1]);

			Assert.assertEquals(param[3], "" + groupComparator.compare(w1, w2));
		}

	}
}