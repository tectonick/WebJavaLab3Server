package LR3_Server;
import java.util.*;
import java.net.*;
import java.io.*;

public class Server {
	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	private static PrintWriter out;
	private static BufferedReader in;
	private static OutputStream rawout;
	private static InputStream rawin;
	
	private static FileOutputStream outFile = null;

	final static int PORT=49011;
	final static String TMPFILENAME = "tmpdata.xml";
	final static String SCHEMAFILENAME= "schema.xml";

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

	public static void stop() {
		try {
			in.close();
			out.close();
			clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		try {
			serverSocket = new ServerSocket(PORT);
			outFile = new FileOutputStream(TMPFILENAME);
			System.out.println("Server started on port "+PORT);
			clientSocket = serverSocket.accept();
			System.out.println("New client"+clientSocket.getLocalAddress());
			rawout=clientSocket.getOutputStream();
			rawin=clientSocket.getInputStream();
			
	        out = new PrintWriter(rawout, true);
	        in = new BufferedReader(new InputStreamReader(rawin));

			XmlParser xmlparser=null;
			String responseMessage="OK";
			String parser= in.readLine();
			switch (parser) {
			case "DOM":
				xmlparser=new DomParser();
				break;
			case "SAX":
				xmlparser=new SaxParser();
				break;
			case "StAX":
				xmlparser=new StAxParser();
				break;
			default:
				responseMessage="NO PARSER";
				break;
			}		    
			out.println(responseMessage);
			out.flush();

			char[] buf=new char[1024];
			int read = in.read(buf);
			if (read!=-1) {
				byte[] data=new String(buf).getBytes("UTF-8");
				outFile.write(data, 0, read);
				outFile.flush();
			}
			outFile.close(); 

			XmlValidator xmlvalidator= new XmlValidator();
			boolean isvalid=xmlvalidator.validate(TMPFILENAME,SCHEMAFILENAME);
			if (isvalid) {
				List<Note> notes=xmlparser.parse(TMPFILENAME);
				ObjectOutputStream outobj = new ObjectOutputStream(rawout);
				outobj.writeObject(notes);
				outobj.close();
			}			
			stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
