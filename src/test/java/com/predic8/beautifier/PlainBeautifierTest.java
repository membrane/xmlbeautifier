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
