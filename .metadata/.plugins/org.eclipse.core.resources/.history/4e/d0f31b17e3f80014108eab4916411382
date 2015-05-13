package ca.effpro.learn.hadoop.mr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.log4j.Logger;

import ca.effpro.hadoop.mrbasics.simple.Mappr;

public abstract class MRBase extends Configured implements Tool {
	private static final Logger logger = Logger.getLogger(MRBase.class);

	private String inputFileDir;
	private String outputFileDir;
	
	protected void printHelp() {
		logger.fatal("Following parameters are needed : ");
		logger.fatal("<input dir>");
		logger.fatal("<output dir>");
	}

	protected int parserArguments(String[] args) throws Exception {

		if (args.length != 2) {
			printHelp();
			return -1;
		}

		inputFileDir = args[0];
		outputFileDir = args[1];
		
		return 1;
	}

	protected String getInputFileDir() {
		return inputFileDir;
	}

	protected String getOutputFileDir() {
		return outputFileDir;
	}
}