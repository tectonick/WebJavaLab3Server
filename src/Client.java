

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import LR3_Server.Note;



public class Client {
	private static Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;

    private static FileInputStream inFile;

    
    final static int PORT=49011;
    final static String IP="127.0.0.1";

    public static void startConnection(String ip, int port) {
        try {
			clientSocket = new Socket(ip, port);
	        out = new PrintWriter(clientSocket.getOutputStream(), true);
	        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static String sendMessage(String msg) {
        out.println(msg);
        String resp=null;
		try {
			resp = in.readLine();
	        return resp;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp;

    }

    public static void stopConnection() {
        try {
			in.close();
	        out.close();
	        clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
	
	public static void main(String[] args) {
    	System.out.println("Client started ");
		startConnection(IP,PORT);
    	System.out.println("Client connected ");
		//sendMessage("Hello");
    	
    	
    	
    	try {
    		out.println("DOM");
    		out.flush();
    		String response = in.readLine();
    		
    		if (response.equals("OK")) {
    			System.out.println("Client got OK. Sending file.");
    			inFile= new FileInputStream("data.xml");
    			byte[] data=inFile.readAllBytes();
    			String text = new String(data, "UTF-8");
    			char[] chars = text.toCharArray();
    			out.write(chars);
    			out.flush();
    			inFile.close();
    			
    			//LR3_Server.Note noteee;
    			ObjectInputStream inobj = new ObjectInputStream(clientSocket.getInputStream());
    			Object obj=inobj.readObject();
    			List<Note> notes = (List<Note>) obj;
    	        in.close();
    	        
    	        for(Note note:notes) {
    	        	System.out.println(note.getId());
    	        	System.out.println(note.getTo());
    	        	System.out.println(note.getFrom());
    	        	System.out.println(note.getHeading());
    	        	System.out.println(note.getBody());
    	        	System.out.println();
    	        }
    		}  		

			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
		stopConnection();
	}

}
