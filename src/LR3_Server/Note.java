package LR3_Server;

import java.io.Serializable;


/**
 * Класс заметки.
 * 
 * @author      Николай Киселев <korn9509@gmail.com>
 * @version     1.0
 * @since       1.0
 */
public class Note implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String to;
	private String from;
	private String heading;
	private String body;
	
	public Note(String id, String to, String from, String heading, String body) {
		this.setId(id);
		this.setTo(to);
		this.setFrom(from);
		this.setHeading(heading);
		this.setBody(body);
	}
	
	public Note() {
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
