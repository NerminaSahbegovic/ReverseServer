package client;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ReverseClient {

	public static void main(String[] args) {
		
		if(args.length < 2) return;
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		
		try(Socket socket = new Socket(host, port)){
			
			
			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output,true);
			
			Console console = System.console();
			String text;
			
			do {
				text = console.readLine("Enter text: ");
				writer.println(text);
				
				InputStream input = socket.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				
				String answer = reader.readLine();
				System.out.println(answer);
				
			}while(!text.equals("bye")); 
			
			socket.close();
			
		}catch(UnknownHostException ex ) {
			System.out.println("There is no host.");
			ex.printStackTrace();
			
		}catch(IOException e) {
			System.out.println("I/O Error " + e.getMessage());
			
		}
	}

}
