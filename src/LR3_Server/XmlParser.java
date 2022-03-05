package LR3_Server;

import java.util.*;


/**
 * Базовый интерфейс парсера xml.
 * 
 * @author      Николай Киселев <korn9509@gmail.com>
 * @version     1.0
 * @since       1.0
 */
public interface XmlParser {
	
	/**
	 * Главный метод парсера.
	 * @param tmpfile Местоположение файла xml на сервере
	 * @return List<Note>
	 */
	List<Note> parse(String tmpfile);	
}
