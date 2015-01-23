package com.predic8.beautifier;

import java.io.IOException;
import java.io.Writer;

public abstract class AbstractXMLBeautyfierFormatter implements XMLBeautifierFormatter {

	protected Writer writer;

	protected int indent;

	public AbstractXMLBeautyfierFormatter(Writer writer, int indent) {
		setWriter(writer);
		setIndent(indent);
	}

	public void indent() throws IOException {
		for (int i = 0; i < indent; i++) {
			writer.write(" ");
		}
	}

	public void setIndent(int indent) {
		this.indent = indent;
	}

	public void setWriter(Writer writer) {
		if (writer == null) {
			throw new IllegalArgumentException("Writer can not be null.");
		}
		this.writer = writer;
	}

	public void incrementIndentBy(int value) {
		indent += value;
	}

	public void decrementIndentBy(int value) {
		indent -= value;
	}

	public void writeText(String text) throws IOException {
		writer.write(text);
	}
	
	public void closeEmptyTag()  throws IOException {
		writer.write(" />");
	}

	public void closeTag() throws IOException {
		writer.write(">");
	}	
}
