package LR3_Server;

import java.util.*;


/**
 * ������� ��������� ������� xml.
 * 
 * @author      ������� ������� <korn9509@gmail.com>
 * @version     1.0
 * @since       1.0
 */
public interface XmlParser {
	
	/**
	 * ������� ����� �������.
	 * @param tmpfile �������������� ����� xml �� �������
	 * @return List<Note>
	 */
	List<Note> parse(String tmpfile);	
}
