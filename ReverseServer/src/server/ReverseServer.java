package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 
 * */
public class ReverseServer {

	public static void main(String[] args) {
		
		if(args.length < 1) return;
		
		int port = Integer.parseInt(args[0]);
		
		try(ServerSocket sSocket = new ServerSocket(port);){
			
			System.out.println("Server is listening on port " + port);
			
			while(true) {
				
				Socket socket = sSocket.accept();
				System.out.println("New client connected!");
				
				InputStream clientInput = socket.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(clientInput));
				
				OutputStream output = socket.getOutputStream();
				PrintWriter writer = new PrintWriter(output,true);
				
				String text;
				
				do {
					text = reader.readLine();
					String reverseText = new StringBuilder(text).reverse().toString();
					writer.println("Server: " + reverseText);
				}while(!text.equals("bye"));
				
				//socket.close();
			}
			
		}catch(IOException e) {
			System.out.println("Server error " + e.getMessage());
			e.printStackTrace();
		}

	}

}
