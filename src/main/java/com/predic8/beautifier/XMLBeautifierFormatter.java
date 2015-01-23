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
