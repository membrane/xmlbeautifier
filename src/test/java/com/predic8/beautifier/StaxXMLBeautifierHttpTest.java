package com.predic8.beautifier;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import junit.framework.TestCase;

import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

public class StaxXMLBeautifierHttpTest extends TestCase {

	XMLBeautifier beautifier; 
	
	InputStream inputStream; 
	
	InputStream inputStream2; 
	
	Writer writer = new OutputStreamWriter(System.out);
	
	@Override
	protected void setUp() throws Exception {
		XMLBeautifierFormatter formatter = new PlainBeautifierFormatter(writer, 2);
		beautifier = new XMLBeautifier(formatter);
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
//		inputStream.close();
//		writer.close();
//		super.tearDown();
	}
	
	
	public void testParser() throws Exception {
//		WebConversation wc = new WebConversation();
//		WebResponse resp = wc.getResponse("http://euro2008.dataaccess.eu/footballpoolwebservice.wso");
//		inputStream = resp.getInputStream();
//		beautifier.parse(new InputStreamReader(inputStream));
	}
}
