/* Copyright 2008-2015 predic8 GmbH, www.predic8.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. */

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
