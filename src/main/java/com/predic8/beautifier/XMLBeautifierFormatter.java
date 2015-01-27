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

public interface XMLBeautifierFormatter {

	public void setIndent(int indent);
	
	public void setWriter(Writer writer);
	
	public void indent() throws IOException;
	
	public void closeTag() throws IOException;
	
	public void startTag() throws IOException;
	
	public void closeEmptyTag() throws IOException;
	
	public void incrementIndentBy(int value);
	
	public void decrementIndentBy(int value);
	
	public void writeNamespaceAttribute(String prefix, String nsUri) throws IOException;
	
	public void writeComment(String text) throws IOException;
	
	public void printNewLine() throws IOException;
	
	public void writeVersionAndEncoding(String version, String encoding) throws IOException;
	
	public void writeAttribute(String prefix, String localName, String value) throws IOException;
	
	public void writeText(String text) throws IOException;
	
	public void writeTag(String prefix, String localName) throws IOException;
	
	public void closeTag(String prefix, String localName) throws IOException;
	
}
