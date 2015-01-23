package com.predic8.beautifier;

import java.io.IOException;
import java.io.Writer;

public class HtmlBeautifierFormatter extends AbstractXMLBeautyfierFormatter {

	public HtmlBeautifierFormatter(Writer writer, int indent) { 
		super(writer, indent);
	}
	
	public void writeTag(String prefix, String localName) throws IOException {
		if (localName == null) {
			return;
		}
		if (prefix != null && !"".equals(prefix)) {
			writer.write("<span class='p'>" + prefix + "</span>:<span class='e'>" + localName);
		} else {
			writer.write("<span class='e'>" + localName);
		}
	} 
	
	public void writeNamespaceAttribute(String prefix, String nsUri) throws IOException {
		if (nsUri == null) {
			return;
		}
		if (prefix != null && !"".equals(prefix)) {
			writer.write("<span class='a'> xmlns:" + prefix + "</span>=\"");
			writer.write("<span class='v'>" + nsUri + "</span>\"");
		} else {
			writer.write("<span class='a'> xmlns" + "" +"</span>=\"");
			writer.write("<span class='v'>" + nsUri + "</span>\"");
		}
	}
	
	public void writeAttribute(String prefix, String localName, String value) throws IOException {
		if (localName == null || value == null) {
			return;
		}
		if (prefix != null && !"".equals(prefix))
			writer.write("<span class='a'>" + prefix +":</span>");
						
		if (value.startsWith("http://"))
		{
			writer.write("<span class='a'>" + localName + "</span>=\"");
			writer.write("<span class='v'><a href=\"/restgate/index.do?URL=" + value + "\">" + value + "</a></span>\"");
		}
		else{
			writer.write("<span class='a'>" + localName + "</span>=\"");
			writer.write("<span class='v'>" + value + "</span>\"");
		}
	}
	

	public void writeComment(String text) throws IOException {
		writer.write("<div class='c'>");
		writer.write("&lt;!--");
		writer.write(HTMLEntityEncode(text));
		writer.write("-->");
		writer.write("</div>");
	}


	private static String HTMLEntityEncode(String s) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0'
					&& c <= '9'|| c==':' || c=='/' || c=='\\' || c=='+' || c=='-' || c=='*'){
				buf.append(c);
			} else {
				buf.append("&#" + (int) c + ";");
			}
		}
		return buf.toString();
	}
	
	public void startTag() throws IOException {
		writer.write("&lt;");
	}
	
	public void printNewLine() throws IOException {
		writer.write("<br/>");
	}
	
	public void writeVersionAndEncoding(String version, String encoding) throws IOException {
		
	}
	
	public void closeTag(String prefix, String localName) throws IOException {
		writer.write("&lt;/");
		writeTag(prefix, localName);
		writer.write("&gt;");
	}
		
}
