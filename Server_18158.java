import java.io.*;
import java.net.*;

public class Server_18158{
	public static void main(String[] args) throws Exception
	{
		
		ServerSocket server_socket = new ServerSocket(1618);
		int Number_clients=0;

		System.out.println("Server Ready\nCurrently Listening.....");
		boolean run=true;

		while(run)
		{
			try
			{
				Socket s=server_socket.accept();
				Number_clients=Number_clients+1;
				System.out.println("Established a TCP Connection with client"+Number_clients);

				ParallelServer pServer = new ParallelServer(s,Number_clients); 
				pServer.start();
			}
			catch(Exception e)
			{
				System.out.println("Failed To Establish Connection");
			}
		}
	}
}

class ParallelServer extends Thread
{
	String line = null;
	DataInputStream Input_from_client = null;
	Socket socket_inuse = null;
	int client=0;

	public ParallelServer(Socket s, int n)
	{
		client=n;
		socket_inuse=s;
	}

	public void run()
	{
		try
		{
			Input_from_client = new DataInputStream(socket_inuse.getInputStream());
			line=Input_from_client.readLine();

			boolean close=false;
			if(line.compareTo("exit")==0)
			{
				close=true;
			}
			while(!close)
			{
				
				System.out.println(""+line+" Client"+client);
				line = Input_from_client.readLine();
				if(line.compareTo("exit")==0)
				{
					close=true;
				}
			}
			System.out.println("Closed Client"+client);
			Input_from_client.close();
			socket_inuse.close();
		}
		catch(Exception e)
		{System.out.println("Failed!!");
		}
	}
}