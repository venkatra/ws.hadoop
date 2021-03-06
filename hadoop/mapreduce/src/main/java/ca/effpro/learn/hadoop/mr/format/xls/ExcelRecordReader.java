package ca.effpro.learn.hadoop.mr.format.xls;

import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import ca.effpro.learn.hadoop.mr.format.zip.MegaZipFileRecordReader;

/**
 * This record reader is used to read an excel file. The assumption for this implementation is that
 * each line is a new record. This reader will not be suited if the excel sheet has <br>
 * <li> merged column
 * <li> calculated column
 * <li> special handling based upon color coding.
 * 
 * 
 * The key would be of the format <sheet name>#<row number>
 * 
 * The value would be each row in the sheet that is currently being processed. The columns would be
 * separated by the column separator variable (which is comma by default).
 *
 */
public class ExcelRecordReader extends RecordReader<Text, Text> {
	private static final Log logger = LogFactory
			.getLog(MegaZipFileRecordReader.class);

	/**
	 * The separator for column values. This will be comma by default.
	 */
	private String columnSeparator = ",";
	
	/**
	 * InputStream used to read the file from the FileSystem
	 */
	private FSDataInputStream fsin;

	/**
	 * The workbook instance
	 */
	private HSSFWorkbook workbook = null;

	/**
	 * The current sheet that is being processed. This will be part of the key
	 */
	private HSSFSheet currentSheet;
	
	private int currentSheetIndex = 0;
	
	private int currentRowNum = 0;

	private Iterator<Row> rowIterator;
	
	private StringBuilder currentValArray = new StringBuilder();
	
	private boolean isFinished = false;
	
	@Override
	public void initialize(InputSplit inputSplit,
			TaskAttemptContext taskAttemptContext) throws IOException,
			InterruptedException {

		FileSplit split = (FileSplit) inputSplit;
		Configuration conf = taskAttemptContext.getConfiguration();
		Path path = split.getPath();
		FileSystem fs = path.getFileSystem(conf);

		// Open the stream
		fsin = fs.open(path);

		try {
			workbook = new HSSFWorkbook(fsin);
			currentSheet = workbook.getSheetAt(currentSheetIndex);
			rowIterator = currentSheet.iterator();
		} catch (IOException e) {
			logger.error("Unable to open workbook [" + path.getName() + "] : "
					+ e.getMessage(), e);
		}
	}

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		if(workbook == null) 
			throw new IOException("The workbook was not initialized. Please check for any exceptions during initialization.");
		
		if(currentSheet == null) {
			currentSheetIndex++;
			
			if(workbook.getNumberOfSheets() >= currentSheetIndex) {
				logger.info("All sheets are processed.");
				isFinished = true;
				return false;
			}
			
			currentSheet = workbook.getSheetAt(currentSheetIndex);
			rowIterator = currentSheet.iterator();
			currentRowNum = 0;
		}
		
		currentValArray = new StringBuilder();
		Row row = rowIterator.next();
		currentRowNum = row.getRowNum();
		
		// For each row, iterate through each columns
		Iterator<Cell> cellIterator = row.cellIterator();

		while (cellIterator.hasNext()) {

			Cell cell = cellIterator.next();

			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN:
				currentValArray.append(cell.getBooleanCellValue() + columnSeparator);
				break;

			case Cell.CELL_TYPE_NUMERIC:
				currentValArray.append(cell.getNumericCellValue() + columnSeparator);
				break;

			case Cell.CELL_TYPE_STRING:
				currentValArray.append(cell.getStringCellValue() + columnSeparator);
				break;
				
			default :
				currentValArray.append(cell.getStringCellValue() + columnSeparator);
				
			}
		}
		
		if(rowIterator.hasNext() == false) {
			currentSheet = null;
		}
		
		
		return true;
	}

	@Override
	public float getProgress() throws IOException, InterruptedException {
		 return isFinished ? 1 : 0;
	}

	/**
	 * The value is a Text, each column value would be seperated by a string (defined by columnSeparator).
	 * 
	 * 
	 */
	@Override
	public Text getCurrentValue() throws IOException, InterruptedException {
		
		if(currentValArray == null)
			return null;
		
		return new Text("" + currentValArray.toString().trim() + "\n");
		
	}
	
	/**
	 * The key would be of the format <sheet name>#<row number>
	 *
	 */
	@Override
	public Text getCurrentKey() throws IOException,
			InterruptedException {
		
		if(currentSheet == null)
			return null;
		
		return new Text(currentSheet.getSheetName() + "#" + currentRowNum);
	}

	@Override
	public void close() throws IOException {

		if (workbook == null)
			return;

		fsin.close();
	}

	protected void setColumnSeparator(String columnSeperator) {
		this.columnSeparator = columnSeperator;
	}
}
