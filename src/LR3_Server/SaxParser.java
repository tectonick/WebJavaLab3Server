package LR3_Server;

import java.io.IOException;
import java.util.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class SaxParser implements XmlParser {

	@SuppressWarnings("deprecation")
	@Override
	public List<Note> parse(InputSource inputSource) {
		XMLReader reader;
		List<Note> notes=null;
		try {
			reader = XMLReaderFactory.createXMLReader();
			NoteSaxHandler handler = new NoteSaxHandler();
			reader.setContentHandler(handler);
			reader.parse(inputSource);
			reader.setFeature("http://xml.org/sax/features/validation", true);
			reader.setFeature("http://xml.org/sax/features/namespaces", true);
			//reader.setFeature("http://apache.org/xml/features/validation/schema",true);
			notes = handler.getNotes();
			for (Note note : notes) {
				System.out.println(note.getHeading());
			}
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notes;


	}
}


class NoteSaxHandler extends DefaultHandler { private
	List<Note> noteList = new ArrayList<Note>(); 
	private Note note;
	private StringBuilder text;
	public List<Note> getNotes() {

		return noteList;
	}
	public void startDocument() throws SAXException {
		System.out.println("Parsing started.");
	}
	public void endDocument() throws SAXException {
		System.out.println("Parsing ended.");
	}
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		System.out.println("startElement -> " + "uri: " + uri + ", localName:" +
				localName+", qName: " + qName); text = new StringBuilder();

			if (qName.equals("note")) note = new Note();
	}
	public void characters(char[] buffer, int start, int length) {
		text.append(buffer, start, length);
	}
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		noteTagName tagName =
				noteTagName.valueOf(qName.toUpperCase().replace("-", "_"));
		switch(tagName){
		case ID:
			note.setId(Integer.parseInt(text.toString()));
			break;
		case TO:
			note.setTo(text.toString());
			break;
		case FROM:
			note.setFrom(text.toString());
			break;
		case HEADING:
			note.setHeading(text.toString());
			break;
		case BODY:
			note.setBody(text.toString());
			break;
		case NOTE:
			noteList.add(note);
			note = null;
			break;
		}
	}
	public void warning(SAXParseException exception) {
		System.err.println("WARNING: line " + exception.getLineNumber() + ": "
				+ exception.getMessage());
	}
	public void error(SAXParseException exception) {
		System.err.println("ERROR: line " + exception.getLineNumber() + ": "
				+ exception.getMessage());
	}
	public void fatalError(SAXParseException exception) throws SAXException {
		System.err.println("FATAL: line " + exception.getLineNumber() + ": "
				+ exception.getMessage());
		throw (exception);
	}
}

enum noteTagName {
TO, FROM, HEADING, BODY, ID, NOTE
}
