package ca.effpro.hadoop.mrbasics.old;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.hadoop.conf.Configured;

public abstract class MRBasicDriverBase extends Configured implements org.apache.hadoop.util.Tool
{
  public MRBasicDriverBase() {}
  
  private static final Log logger = org.apache.commons.logging.LogFactory.getLog(MRBasicDriverBase.class);
  
  protected String inputDir = null;
  protected String outputDir = null;
  protected String jobName = null;
  
  public void initArguments(String[] args) {
    setJobName(args[0]);
    setInputDir(args[1]);
    setOutputDir(args[2]);
  }

protected String getInputDir() {
	return inputDir;
}

protected void setInputDir(String inputDir) {
	this.inputDir = inputDir;
}

protected String getOutputDir() {
	return outputDir;
}

protected void setOutputDir(String outputDir) {
	this.outputDir = outputDir;
}

protected String getJobName() {
	return jobName;
}

protected void setJobName(String jobName) {
	this.jobName = jobName;
}
  
  
}
  