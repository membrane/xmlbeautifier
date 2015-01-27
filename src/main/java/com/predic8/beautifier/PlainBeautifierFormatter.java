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

import java.io.IOException;
import java.io.Writer;

public class PlainBeautifierFormatter extends AbstractXMLBeautyfierFormatter {

	public PlainBeautifierFormatter(Writer writer, int indent) {
		super(writer, indent);
	}
	
	public void writeTag(String prefix, String localName) throws IOException {
		if (localName == null) {
			return;
		}
		if (prefix != null && !"".equals(prefix)) {
			writer.write(prefix + ":" + localName);
		} else {
			writer.write(localName);
		}
	}
	
	public void writeNamespaceAttribute(String prefix, String nsUri) throws IOException {
		if (nsUri == null) {
			return;
		}
		if (prefix != null && !"".equals(prefix)) {
			writer.write(" xmlns:" + prefix);
			writer.write("=\"" + nsUri + "\"");
		} else {
			writer.write(" xmlns=\"" + nsUri + "\"");
		}
		
	}

	public void writeAttribute(String prefix, String localName, String value) throws IOException {
		if (localName == null || value == null) {
			return;
		}
		if (prefix != null && !"".equals(prefix)) {
			writer.write(prefix + ":" + localName + "=\"" + value + "\"");
		} else {
			writer.write(localName + "=\"" + value + "\"");
		}
	}
	
	public void writeComment(String text) throws IOException {
		if (text == null) {
			return;
		}
		writer.write("<!--");
		writer.write(text);
		writer.write("-->");
	}
	
	public void printNewLine() throws IOException {
		writer.write("\r\n");
	}
	
	public void startTag()  throws IOException {
		writer.write("<");
	}
	
	public void writeVersionAndEncoding(String version, String encoding) throws IOException {
		if (version == null) {
			return;
		}
		if (encoding == null) {
			writer.write("<?xml version = \"" + version + "\" ?>");
		} else {
			writer.write("<?xml version = \"" + version + "\" encoding = \"" + encoding + "\" ?>");
		}
		printNewLine();
	}
	
	public void closeTag(String prefix, String localName) throws IOException {
		writer.write("</");
		writeTag(prefix, localName);
		writer.write(">");
	}
	
	
}
