package ca.effpro.learn.hadoop.mr.tpt.exer1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class YearlyCollectionOfAmountToInfractionReducerTest {

	private static final Log logger = LogFactory
			.getLog(YearlyCollectionOfAmountToInfractionReducerTest.class);

	ReduceDriver<YearMonthToInfractionWritable, Text, Text, IntWritable> reduceDriver = ReduceDriver
			.newReduceDriver(new YearlyCollectionOfAmountToInfractionReducer());

	@Before
	public void setUp() {
		System.setProperty("hadoop.home.dir",
				"/Users/d3vl0p3r/Dev/lib/hadoop-2.7.0");
	}

	@Test
	public void testReducer() throws IOException {

		String[][] params = new String[][] { new String[] { "20080627,5", "30,ON|30,ON", "2008,5,60" },

		// new String[]
		// {"20080627,5,30,ON","20080627,5,30,ON","20080627,10,30,ON","2008,5,60|2008,10,30"},

		// new String[]
		// {"20080727,5,30,ON","20080627,5,30,ON","20090627,5,30,ON","20080627,10,30,ON","2008,5,60|2009,5,30|2008,10,30"},
		};

		int idx = 0;

		YearMonthToInfractionWritable k3 = new YearMonthToInfractionWritable();
		List<Text> v3 = new ArrayList<Text>();
		Text k4 = new Text();
		IntWritable v4 = new IntWritable();

		for (String[] dataLine : params) {

			logger.info((idx++) + " => " + StringUtils.join(dataLine, " / "));

			String[] inputs = dataLine[0].split(",");
			k3.set(inputs[0], inputs[1]);

			String[] vals = dataLine[1].split("\\|");
			for (String val : vals) {
				Text valT = new Text(val);
				v3.add(valT);
			}

			String[] outputs = dataLine[2].split(",");
			k4.set(outputs[0] + "," + outputs[1]);
			v4.set(Integer.parseInt(outputs[2]));

			reduceDriver.withInput(k3, v3);
			reduceDriver.withOutput(k4, v4);
			reduceDriver.runTest();

		}

	}

}
