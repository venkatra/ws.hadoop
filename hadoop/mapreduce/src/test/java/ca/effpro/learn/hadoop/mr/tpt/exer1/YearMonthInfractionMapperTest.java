package ca.effpro.learn.hadoop.mr.tpt.exer1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

public class YearMonthInfractionMapperTest {

	private static final Log logger = LogFactory
			.getLog(YearMonthInfractionMapperTest.class);

	MapDriver<Text, Text, YearMonthToInfractionWritable, Text> mapDriver = MapDriver.newMapDriver(new YearMonthInfractionMapper());

	@Before
	public void setUp() {
		System.setProperty("hadoop.home.dir",
				"/Users/d3vl0p3r/Dev/lib/hadoop-2.7.0");
	}

	@Test
	public void testMapper() throws IOException {

		String[][] params = new String[][] { 
				//set 1
				new String[] {"SET1",
								"***19415,20080627,5,PARK-HWY DRNG PROH TIMES/DAYS,30,0035,NR,1095 ST CLAIR AVE W,,,ON"
								+ "\n***07839,20070517,29,PARK PROHIBITED TIME NO PERMIT,30,0035,NR,69 HOGARTH AVE,,,ON"
				},
				
				
		};

		//YearMonthToInfractionWritable k2 = new YearMonthToInfractionWritable();
		Text k1, v1, v2;

		List<Pair<YearMonthToInfractionWritable, Text>> outputPairs = new ArrayList<Pair<YearMonthToInfractionWritable, Text>>();
		Pair<YearMonthToInfractionWritable, Text> outputPair;
		int idx = 0;
		
		for (String[] dataLine : params) {
			outputPairs.clear();
			
			logger.info("\n\n\n\n " + (idx++) + " => " + StringUtils.join(dataLine, " / "));
			
			k1 = new Text(dataLine[0]);
			v1 = new Text(dataLine[1]);
			
			String[] dataLines = dataLine[1].split("\n");
			String[] valArray;
			String dateOfInfraction, infractionCode, fineAmount, province;

			for (String line : dataLines) {
				line  = line.replace(" ","");
				valArray = line.split(",");
				dateOfInfraction = valArray[1];
				infractionCode = valArray[2];
				fineAmount = valArray[4];
				province = valArray[10];
				
				YearMonthToInfractionWritable k2 = new YearMonthToInfractionWritable();
				
				k2.set(dateOfInfraction, infractionCode);
				v2 = new Text(fineAmount+"," + province);
				
				outputPair = new Pair<YearMonthToInfractionWritable, Text>(k2, v2);
				outputPairs.add(outputPair);
			}
					
			mapDriver.withInput(k1, v1);
			mapDriver.withAllOutput(outputPairs);
			mapDriver.runTest();
		}

	}

}
