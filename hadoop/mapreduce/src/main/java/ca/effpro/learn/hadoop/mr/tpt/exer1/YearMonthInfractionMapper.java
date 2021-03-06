package ca.effpro.learn.hadoop.mr.tpt.exer1;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Mapper class to extract information from the toronto parking data set. The
 * input dataset is the output of program TransformZipToSequenceFileDriver.java.
 * 
 * The value of the data records are stored in the format as in the below sample
 * : <br>
 * <br>
 * tag_number_masked,date_of_infraction,infraction_code,infraction_description,
 * set_fine_amount
 * ,time_of_infraction,location1,location2,location3,location4,province
 * ***63611,20080101,3,PARK/LEAVE ON PRIVATE PROPERTY,30,,,364 EAST MALL,,,ON
 * ***99026,20080101,9,STOP HWY PROHIBITED TIME/DAY,60,,,80 RICHMOND ST W,,,ON <br>
 * 
 * This mapper class extract the following information from the dataset.
 * <ol>
 * date of infraction (YYYYMM) => will be part of composite key
 * <ol>
 * infraction code => will be part of composite key
 * <ol>
 * set fine amount => will be present in value
 * <ol>
 * province => will be present in value
 * <ol>
 * 
 */
public class YearMonthInfractionMapper extends
		Mapper<Text, Text, YearMonthToInfractionWritable, Text> {

	private static final Log logger = LogFactory
			.getLog(YearMonthInfractionMapper.class);

	private YearMonthToInfractionWritable k2 = new YearMonthToInfractionWritable();
	private Text v2 = new Text();

	@Override
	public void map(Text k1, Text v1, Context context) throws IOException,
			InterruptedException {

		
		String val$String$line = v1.toString();
		val$String$line = val$String$line.replaceAll("[^a-zA-Z0-9,\\n\\*]","");
		
		String[] val$String = val$String$line.split("\n");
		String[] valArray;
		String dateOfInfraction, infractionCode, fineAmount, province;
		long skipRecordCount = 0;
		long processedRecordCount =0;
		
		for (String line : val$String) {

			if (StringUtils.isBlank(line)) {
				skipRecordCount++;
				continue;
			}

			if (line.indexOf("date") != -1) {
				skipRecordCount++;
				continue;
			}

			valArray = line.split(",");

			if (valArray.length < 10) {
				skipRecordCount++;
				continue;
			}

			dateOfInfraction = valArray[1];
			infractionCode = valArray[2];
			fineAmount = valArray[4];
			province = (valArray.length >= 10) ? valArray[10] : "XX";
			
			k2.set(dateOfInfraction, infractionCode);
			v2.set(fineAmount + "," + province);

			context.write(k2, v2);
			processedRecordCount++;

		}

		Counter processedRecordCounter = context.getCounter(Exer1CounterEnum.class.getName(),
				Exer1CounterEnum.PROCESSED_RECORD_COUNTER.toString());
		processedRecordCounter.increment(processedRecordCount);
		
		Counter invalidRecordCounter = context.getCounter(Exer1CounterEnum.class.getName(),
				Exer1CounterEnum.INVALID_RECORD_COUNTER.toString());
		invalidRecordCounter.increment(skipRecordCount);
		
	}
}
