package ca.effpro.learn.hadoop.mr.tpt.avro;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Mapper;

import ca.effpro.learn.hadoop.mr.tpt.exer1.Exer1CounterEnum;

public class ZipFileEntriesToTextMapper extends
		Mapper<Text, BytesWritable, Text, Text> {
	private static final Log logger = LogFactory
			.getLog(ZipFileEntriesToTextMapper.class);

	private Text k2 = new Text();
	private Text v2 = new Text();
	
	private String fromPreviousRow = "";
	private int i=0;
	
	public void map(Text k1, BytesWritable v1,
			Mapper<Text, BytesWritable, Text, Text>.Context context)
			throws IOException, InterruptedException {

		String val$String$line = new String(v1.getBytes());
		val$String$line = val$String$line.replaceAll("[^a-zA-Z0-9,\\n\\*]","");
		
		String row = fromPreviousRow + val$String$line;
		int idx = row.lastIndexOf('\n');

		fromPreviousRow = row.substring(idx);
		
		String[] dataRows = row.substring(0, idx).split("\n");
		String[] cols;
		long skipRecordCount = 0;
		long processedRecordCount =0;
		
		for(String dataRow : dataRows) {
		
			if (StringUtils.isBlank(dataRow)) {
				skipRecordCount++;
				continue;
			}

			if (dataRow.indexOf("date") != -1) {
				skipRecordCount++;
				continue;
			}

			if (dataRow.startsWith("**") == false) {
				skipRecordCount++;
				continue;
			}
			
			cols = dataRow.split(",");

			if (cols.length < 10) {
				skipRecordCount++;
				continue;
			}
			
			v2.set(dataRow);
			k2.set("" + (i++));
			processedRecordCount++;
			
			context.write(k2, v2);
		}
		
		Counter processedRecordCounter = context.getCounter(Exer1CounterEnum.class.getName(),
				Exer1CounterEnum.PROCESSED_RECORD_COUNTER.toString());
		processedRecordCounter.increment(processedRecordCount);
		
		Counter invalidRecordCounter = context.getCounter(Exer1CounterEnum.class.getName(),
				Exer1CounterEnum.INVALID_RECORD_COUNTER.toString());
		invalidRecordCounter.increment(skipRecordCount);
		
		
	}
}