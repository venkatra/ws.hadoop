package ca.effpro.learn.datasource.rss;

import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;
import com.rometools.rome.io.XmlReader;

public class RSSSubscriber extends Thread {
	private static final Log logger = LogFactory.getLog(RSSSubscriber.class);

	private static final String RSS$KIJIJI_BUY_SELL_ALL = "http://www.kijiji.ca/rss-srp-buy-sell/gta-greater-toronto-area/c10l1700272";
	private static final String RSS$KIJIJI_ELECTRONICS = "http://www.kijiji.ca/rss-srp-electronics/gta-greater-toronto-area/c15l1700272";

	private URL feedUrl;
	private String outputFile;
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	private SyndFeedInput input = new SyndFeedInput();
	private long sleepTime = 1;

	// This field is volatile because two different threads may access it
	volatile boolean keepRunning = true;

	public void run() {
		SyndFeed feed;
		//SyndEntry entry;
		String lastGUID = "", guid;
		int i=0;
		boolean lastGUIDFound = false;
		InputStream urlInputStream;
		String rssFeedAsString;
		XmlReader xmlReader;
		
		while (keepRunning) {
			try {
				feed = input.build(new XmlReader(feedUrl));
				logger.info(" *** Published Date : " + feed.getPublishedDate());
				
				i=0;
				lastGUIDFound = false;
				for(SyndEntry entry : feed.getEntries()) {
					guid = entry.getUri();

					if(guid.equals(lastGUID)) {
						lastGUIDFound = true;
						break;
					}
					i++;
				}
				
				lastGUID = feed.getEntries().get(0).getUri();
				
				if(lastGUIDFound) {
					logger.info("Newer entry count : " + i);
					
					StringWriter writer = new StringWriter();
					SyndFeedOutput output = new SyndFeedOutput();
					output.output(feed,writer);
					rssFeedAsString = writer.toString();
					logger.info(rssFeedAsString.replaceAll("\n", "").replaceAll("\r", "").trim());
				}
				else
					logger.info(" last GUID was not found");
				
			} catch (Exception ex) {
				logger.error("Feed exception : " + ex.getMessage());
			}
			
			try {
				Thread.sleep(1000*60 * sleepTime);
			} catch (InterruptedException ignore) {
				//ignore
			}
		}
	}

	public static void main(String[] args) throws MalformedURLException {
		RSSSubscriber subscriber = new RSSSubscriber();
		subscriber.setFeedUrl(RSS$KIJIJI_ELECTRONICS);
		subscriber.setOutputFile("src/test/data/kijijiRss.txt");
		subscriber.start();
		// thread.pleaseStop();
		while (1 == 1) {
			try {
				Thread.sleep(60 * 60 * 5);
			} catch (InterruptedException ignore) {
				// ignore
			}
		}
	}

	public RSSSubscriber() {
		setDaemon(true);
	}

	public void pleaseStop() {
		keepRunning = false;
	}

	public void setFeedUrl(String feedUrlStr) throws MalformedURLException {
		this.feedUrl = new URL(feedUrlStr);
	}

	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}

}
