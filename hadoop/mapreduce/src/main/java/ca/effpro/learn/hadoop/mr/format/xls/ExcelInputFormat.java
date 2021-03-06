package ca.effpro.learn.hadoop.mr.format.xls;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

/**
 * 
 * The input format to read excel files. By default this class will use the
 * excelRecordReader implementation but can be overridden.
 * 
 *
 */
public class ExcelInputFormat extends FileInputFormat<Text, Text> {
	
	private RecordReader<Text, Text> recordReader = new ExcelRecordReader();
	
	/**
	 * excel files are not splitable
	 */
	@Override
	protected boolean isSplitable(JobContext context, Path filename) {
		return false;
	}

	/**
	 * Create the RecordReader to parse the file
	 */
	@Override
	public RecordReader<Text, Text> createRecordReader(InputSplit split,
			TaskAttemptContext context) throws IOException,
			InterruptedException {
		return recordReader;
	}

	public void setRecordReader(RecordReader<Text, Text> recordReader) {
		this.recordReader = recordReader;
	}


}
