package ca.effpro.learn.hadoop.mr.format.xls;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ExcelSheetRecordMapper extends
		Mapper<Text, Text, Text, Text> {
//	private static final Log logger = LogFactory
//			.getLog(ExcelSheetRecordMapper.class);

	public void map(Text key, Text value,
			Mapper<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {

		context.write(key, value);
	}
}