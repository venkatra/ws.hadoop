package ca.effpro.learn.hadoop.mr.format.zip;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;

/**
 * This RecordReader is used to read large files which are zipped and stored in HDFS. Foe each file, 
 * the content are read and store in value. 
 * 
 * One each call to nextKeyValue; only the size determined by BYTE_SIZE is read and returned as value.
 * 
 */
public class MegaZipFileRecordReader extends ZipFileRecordReader {
	private static final Log logger = LogFactory.getLog(MegaZipFileRecordReader.class);
	private static final int BYTE_SIZE = 1024;

	private ZipEntry entry = null;

	private int previousOffset = 0;

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {

		if (entry == null) {

			try {
				entry = zip.getNextEntry();
			} catch (ZipException e) {
				if (ZipFileInputFormat.getLenient() == false)
					throw e;
			}

			// Sanity check
			if (entry == null) {
				isFinished = true;
				return false;
			}

			// Filename
			currentKey = new Text(entry.getName());
			previousOffset = 0;
		}

		logger.info("Offset : " + previousOffset );
		// Read the file contents
		int bytesRead = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		byte[] temp = new byte[BYTE_SIZE];
		try {
			bytesRead = zip.read(temp, 0, BYTE_SIZE);
		} catch (EOFException e) {
			if (ZipFileInputFormat.getLenient() == false)
				throw e;
			return false;
		}

		if (bytesRead <= 0) {
			logger.info("Finished reading file : " + entry.getName() );
			zip.closeEntry();
			entry = null;
			previousOffset = 0;

		} else {
			logger.info("Total bytes read of file [" + entry.getName() + "] : " + (previousOffset + bytesRead));
			bos.write(temp, 0, bytesRead);
			currentValue = new BytesWritable(bos.toByteArray());
			previousOffset += bytesRead;
		}

		return true;
	}
	
}