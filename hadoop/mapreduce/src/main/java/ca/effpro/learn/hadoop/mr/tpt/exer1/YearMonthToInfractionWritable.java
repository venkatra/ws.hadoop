package ca.effpro.learn.hadoop.mr.tpt.exer1;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

/**
 * A custom writable class which will be composed of the following items
 * <ol>
 * year
 * <ol>
 * month
 * <ol>
 * infraction code
 * 
 */
public class YearMonthToInfractionWritable implements
		WritableComparable<YearMonthToInfractionWritable> {

	private static final Log logger = LogFactory
			.getLog(YearMonthToInfractionWritable.class);

	private IntWritable year = new IntWritable();
	private IntWritable month = new IntWritable();
	private Text infractionCode = new Text();
	
	/**
	 * This is used during comparison. this would not be stored
	 */
	private volatile Date dt;

	public void set(String p$yearMonth, String p$infractionCode) {
		
		year.set(Integer.parseInt(p$yearMonth.substring(0, 4)));
		month.set(Integer.parseInt(p$yearMonth.substring(5, 6)));
		infractionCode.set(p$infractionCode);

		generateDate(year, month);

	}

	public void set(IntWritable p$year, IntWritable p$month,
			Text p$infractionCode) {
		year.set(p$year.get());
		month.set(p$month.get());
		infractionCode.set(p$infractionCode);

		generateDate(year, month);
	}

	private void generateDate(IntWritable p$year, IntWritable p$month) {
		Calendar c = new GregorianCalendar(p$year.get(), p$month.get() - 1, 1,
				0, 0, 0);
		dt = c.getTime();
	}

	public int compareTo(YearMonthToInfractionWritable p$writable) {
		int cmp = dt.compareTo(p$writable.dt);
		if (cmp != 0)
			return cmp;
		
		int cd1 = Integer.parseInt(infractionCode.toString());
		int cd2 = Integer.parseInt(p$writable.infractionCode.toString());
		
		return Integer.compare(cd1, cd2);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;
		if ((obj instanceof YearMonthToInfractionWritable) == false)
			return false;

		return (this.compareTo((YearMonthToInfractionWritable) obj) == 0);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		// you pick a hard-coded, randomly chosen, non-zero, odd number
		// ideally different for each class
		return new HashCodeBuilder(17, 37).append(year).append(month)
				.append(infractionCode).toHashCode();
	}

	public void readFields(DataInput din) throws IOException {
		year.readFields(din);
		month.readFields(din);
		infractionCode.readFields(din);

		// called the set method to initialize the date
		set(year, month, infractionCode);

	}

	public void write(DataOutput dout) throws IOException {
		year.write(dout);
		month.write(dout);
		infractionCode.write(dout);
	}

	public IntWritable getYear() {
		return year;
	}

	public IntWritable getMonth() {
		return month;
	}

	public Text getInfractionCode() {
		return infractionCode;
	}

}
