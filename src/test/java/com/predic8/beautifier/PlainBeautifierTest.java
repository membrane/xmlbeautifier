package com.predic8.beautifier;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;

import junit.framework.Assert;
import junit.framework.TestCase;

public class PlainBeautifierTest extends TestCase {

	XMLBeautifier beautifier; 
	
	InputStream inputStream;
	
	Writer writer;
	@Override
	protected void setUp() throws Exception {		
		File base = new File("target/test");
		base.mkdirs();
		writer = new FileWriter(new File(base, "plain_drain.xml"));
		inputStream = this.getClass().getResourceAsStream("/BLZService.xml");
		XMLBeautifierFormatter formatter = new PlainBeautifierFormatter(writer, 0);
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
		writer.close();
		Assert.assertEquals(new File("src/test/resources/plain_drain.html").length(), new File("target/test/plain_drain.html").length());
	}
}
