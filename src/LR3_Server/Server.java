package LR3_Server;
import java.util.*;


public class Server {

	public static void testParsers() {
		List<Note> notes=null;
		DomParser dom=new DomParser();
		SaxParser sax=new SaxParser();
		StAxParser stax=new StAxParser();
		
		notes = stax.parse("data.xml");
		for (Note note:notes) {
			System.out.println(note.getHeading());
		}

	}
	
	public static void main(String[] args) {
		
		testParsers();

	}

}
