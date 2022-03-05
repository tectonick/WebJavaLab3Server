package LR3_Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.*; 
import org.apache.xerces.parsers.DOMParser;
import org.xml.sax.SAXException;

public class DomParser implements XmlParser {

	@Override
	public List<Note> parse(String tmpfile) {
		
		DOMParser parser = new DOMParser();
		List<Note> notes=null; 
		try {
			parser.parse(tmpfile);
			Document document = parser.getDocument();
			Element root = document.getDocumentElement();
			notes = new ArrayList<Note>();
			NodeList noteNodes = root.getElementsByTagName("note");
			Note note = null;
			for (int i = 0; i < noteNodes.getLength(); i++) {
				note = new Note();
				Element noteElement = (Element) noteNodes.item(i);
				note.setId(Integer.parseInt(getSingleChild(noteElement,"id").getTextContent().trim()));
				note.setTo(getSingleChild(noteElement,"to").getTextContent().trim());
				note.setFrom(getSingleChild(noteElement,"from").getTextContent().trim());
				note.setHeading(getSingleChild(noteElement,"heading").getTextContent().trim());
				note.setBody(getSingleChild(noteElement,"body").getTextContent().trim());
				notes.add(note);
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
	
	private Element getSingleChild(Element element, String childName){
		NodeList nlist = element.getElementsByTagName(childName);
		Element child = (Element) nlist.item(0);
		return child;
	}

}
