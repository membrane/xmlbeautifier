package com.predic8.beautifier;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;

import junit.framework.Assert;
import junit.framework.TestCase;

public class HtmlBeautifierTest extends TestCase {

	XMLBeautifier beautifier; 
	
	InputStream inputStream; 
	
	Writer writer;
	@Override
	protected void setUp() throws Exception {
		File base = new File("target/test");
		base.mkdirs();
		writer = new FileWriter(new File(base, "html_drain.html"));
		
		inputStream = this.getClass().getResourceAsStream("/BLZService.xml");
		writer.write("<html><head><link rel='stylesheet' href='style.css' /></head><body><pre>");
		XMLBeautifierFormatter formatter = new HtmlBeautifierFormatter(writer, 0);
		beautifier = new XMLBeautifier(formatter);
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		inputStream.close();
		super.tearDown();
	}
	
	public void testFile() throws Exception {
		beautifier.parse(new InputStreamReader(inputStream));
		writer.write("</pre></body></html>");
		writer.close();
		Assert.assertEquals(new File("src/test/resources/html_drain.html").length(), new File("target/test/html_drain.html").length());
	}
}
