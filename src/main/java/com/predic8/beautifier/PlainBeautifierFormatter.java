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
