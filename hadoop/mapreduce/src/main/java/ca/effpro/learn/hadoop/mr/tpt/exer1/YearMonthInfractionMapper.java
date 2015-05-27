package ca.effpro.learn.hadoop.mr.tpt.exer1;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.Text;
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

	private YearMonthToInfractionWritable compositeKey$YearMonthToInfractionCode = new YearMonthToInfractionWritable();
	private Text mapValue = new Text();

	@Override
	public void map(Text key, Text value, Context context) throws IOException,
			InterruptedException {

		String val$String$line = value.toString();
		String[] val$String = val$String$line.split("\n");
		String[] valArray;
		String dateOfInfraction, infractionCode, fineAmount, province;

		for (String line : val$String) {
			valArray = line.split(",");
			dateOfInfraction = valArray[1];
			infractionCode = valArray[2];
			fineAmount = valArray[4];
			province = valArray[10];

			compositeKey$YearMonthToInfractionCode.set(dateOfInfraction,
					infractionCode);
			mapValue.set(
					fineAmount + "," + province);
			
			context.write(compositeKey$YearMonthToInfractionCode, mapValue);

		}

	}
}
