package com.predic8.beautifier;

import java.io.IOException;
import java.io.Reader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class XMLBeautifier {

	private boolean empty;

	private boolean startTagClosed = true;

	private boolean charContent;

	private boolean endCalled;

	private boolean firstElement = true;

	private XMLBeautifierFormatter formatter;

	public XMLBeautifier(XMLBeautifierFormatter formatter) {
		this.formatter = formatter;
	}

	public void parse(Reader reader) throws Exception {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		parse(factory.createXMLStreamReader(reader));
	}

	private void parse(XMLStreamReader reader) throws Exception {
		writeStartDocument(reader);
		while (reader.hasNext()) {
			displayEvent(reader.next(), reader);
		}
	}

	private void indent() throws IOException {
		formatter.indent();
	}

	private void displayEvent(int eventType, XMLStreamReader reader) throws Exception {

		switch (eventType) {
		case XMLStreamConstants.START_ELEMENT:
			if (!startTagClosed) {
				formatter.closeTag();
			}

			if (!endCalled && !firstElement) {
				printNewLine(false);
			}

			if (!charContent) {
				indent();
			}

			formatter.startTag();

			formatter.writeTag(reader.getPrefix(), reader.getLocalName());
			formatter.incrementIndentBy(2);

			int indent = reader.getPrefix() != null ? reader.getPrefix().length() : 0;
			indent += reader.getLocalName().length();
			writeNamespaceAttributes(reader, indent);
			writeAtributes(reader, indent);

			startTagClosed = false;
			charContent = false;
			empty = true;
			endCalled = false;
			firstElement = false;
			break;

		case XMLStreamConstants.END_ELEMENT:
			formatter.decrementIndentBy(2);
			if (empty) {
				formatter.closeEmptyTag();
				empty = false;
				startTagClosed = true;
				printNewLine(true);
				break;
			}
			if (!charContent) {
				indent();
			}

			formatter.closeTag(reader.getPrefix(), reader.getLocalName());
			startTagClosed = true;
			printNewLine(true);
			charContent = false;
			break;
		case XMLStreamConstants.CHARACTERS:
			empty = false;
			if (!startTagClosed) {
				formatter.closeTag();
				startTagClosed = true;
			}

			charContent = containsNonWhitespaceCharacters(reader.getText());
			if (charContent) {
				formatter.writeText(reader.getText());
			}
			break;
		case XMLStreamConstants.COMMENT:
			indent();
			writeComment(reader);
			break;
		case XMLStreamConstants.DTD:
			break;
		case XMLStreamConstants.CDATA:
			break;
		case XMLStreamConstants.ATTRIBUTE:
			break;
		case XMLStreamConstants.END_DOCUMENT:

			break;
		case XMLStreamConstants.START_DOCUMENT:
			writeStartDocument(reader);
			break;
		case XMLStreamConstants.ENTITY_DECLARATION:

			break;
		case XMLStreamConstants.PROCESSING_INSTRUCTION:

			break;
		default:
			break;
		}
	}

	private void writeStartDocument(XMLStreamReader reader) throws IOException {
		indent();
		formatter.writeVersionAndEncoding(reader.getVersion(), reader.getEncoding());
	}

	private boolean containsNonWhitespaceCharacters(String s) {
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c != 10 && c != 13 && c != 32 && c != 9) {
				return true;
			}
		}
		return false;
	}

	private void writeAtributes(XMLStreamReader parser, int indent) throws IOException {
		formatter.incrementIndentBy(indent);
		for (int i = 0; i < parser.getAttributeCount(); i++) {
			if (parser.getNamespaceCount() > 0 || i != 0) {
				formatter.printNewLine();
				formatter.indent();
			}

			formatter.writeText(" ");
			formatter.writeAttribute(parser.getAttributePrefix(i), parser.getAttributeLocalName(i), parser.getAttributeValue(i));
		}
		formatter.decrementIndentBy(indent);
	}

	private void writeComment(XMLStreamReader reader) throws IOException {
		formatter.writeComment(reader.getText());
	}

	private void writeNamespaceAttributes(XMLStreamReader reader, int indent) throws IOException {
		formatter.incrementIndentBy(indent);
		for (int j = 0; j < reader.getNamespaceCount(); j++) {
			if (j != 0) {
				formatter.printNewLine();
				formatter.indent();
			}
			formatter.writeNamespaceAttribute(reader.getNamespacePrefix(j), reader.getNamespaceURI(j));
		}
		formatter.decrementIndentBy(indent);
	}

	private void printNewLine(boolean endElement) throws IOException {
		endCalled = endElement;
		formatter.printNewLine();
	}
}
