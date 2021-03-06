package ca.effpro.learn.hadoop.mr.tpt.exer1;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class YearComparatorTest {
	private static final Log logger = LogFactory
			.getLog(YearComparatorTest.class);

	@Ignore
	@Test
	public void test$Compare() {
		String[][] params = new String[][] {
				new String[] { "20080108", "01", "20080108", "01", "0" },
				new String[] { "20080108", "01", "20080108", "02", "-1" },
				new String[] { "20080108", "02", "20080108", "01", "1" },

				new String[] { "20080108", "01", "20080208", "01", "0" },
				new String[] { "20080108", "01", "20090108", "01", "-1" },
				new String[] { "20080108", "01", "20070108", "01", "1" },
				new String[] { "20080108", "01", "20060108", "01", "1" },

		};

		YearMonthToInfractionWritable w1 = new YearMonthToInfractionWritable();
		YearMonthToInfractionWritable w2 = new YearMonthToInfractionWritable();
		YearComparator rawYrComparator = new YearComparator();

		ByteArrayOutputStream baos1, baos2;
		DataOutputStream dos1, dos2;
		byte[] b1, b2;

		for (String[] param : params) {
			logger.info("=> " + StringUtils.join(param, ","));

			w1.set(param[0], param[1]);
			w2.set(param[2], param[3]);

			baos1 = new ByteArrayOutputStream();
			dos1 = new DataOutputStream(baos1);
			baos2 = new ByteArrayOutputStream();
			dos2 = new DataOutputStream(baos2);
			try {
				w1.write(dos1);
				w2.write(dos2);
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}

			b1 = baos1.toByteArray();
			b2 = baos2.toByteArray();

			Assert.assertEquals(
					param[4],
					""
							+ rawYrComparator.compare(b1, 0, b1.length, b2, 0,
									b2.length));

		}

	}
}