import java.io.*;
import java.net.*;

public class Client_18158{

	private static Socket socket = null;                      
	private static DataInputStream Input_from_console = null;
	private static PrintWriter Stream_to_server = null;

	public static void initialise()
	{
		try
		{
			socket=new Socket("localhost",1618);
			Input_from_console=new DataInputStream(System.in);
			Stream_to_server=new PrintWriter(socket.getOutputStream());

		}
		catch(IOException e)
		{
			System.out.println("IO Exception");
		}
	}

	public static void main(String[] args) 
	{

		
		
		initialise();
		
		System.out.println("Type exit to close the connection Or\nEnter the string to be sent to the server: ");
		

		try
		{
			String line = Input_from_console.readLine();
			while(line.compareTo("exit")!=0)
			{
				Stream_to_server.println(line);
				Stream_to_server.flush();
				line=Input_from_console.readLine();
			}
			Stream_to_server.println(line);
			System.out.println("Closing the connection");
			Stream_to_server.close();
			Input_from_console.close();
			socket.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Server not UP!!");
		}

	}
}