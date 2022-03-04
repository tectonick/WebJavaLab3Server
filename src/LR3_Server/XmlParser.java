package LR3_Server;

import java.util.*;

import org.xml.sax.InputSource;

public interface XmlParser {
	List<Note> parse(InputSource inputSource);	
}
