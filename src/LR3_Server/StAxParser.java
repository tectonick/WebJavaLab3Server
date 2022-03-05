package LR3_Server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.*; 

public class StAxParser implements XmlParser {

	@Override
	public List<Note> parse(String tmpfile) {
			XMLInputFactory	inputFactory = XMLInputFactory.newInstance(); 
			List<Note> notes=null;
			try
			{
				InputStream input = new FileInputStream(tmpfile);
				XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
				notes = process(reader);
			} catch (XMLStreamException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		return notes;
	}
	
	private static List<Note> process(XMLStreamReader reader) throws XMLStreamException {
		List<Note> notes = new ArrayList<Note>();
		Note note = null;
		noteTagName elementName = null;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				elementName = noteTagName.getElementTagName(reader.getLocalName());
				switch (elementName) {
				case NOTE:
					note = new Note(); 
					break;
				}
			break;
			case XMLStreamConstants.CHARACTERS:
				String text = reader.getText().trim();
				if (text.isEmpty()) {
					break;
				}
				switch (elementName) {
					case TO:
						note.setTo(text);
						break;
					case FROM:
						note.setFrom(text);
						break;
					case HEADING:
						note.setHeading(text);
						break;
					case BODY:
						note.setBody(text);
						break;
					case ID:
						
						note.setId(text);
						break;
					}
				break;
			case XMLStreamConstants.END_ELEMENT:
				elementName = noteTagName.getElementTagName(reader.getLocalName());
				switch (elementName) {
					case NOTE:
						notes.add(note);
						break;
				}
			}
		}
		return notes;
	}
}


