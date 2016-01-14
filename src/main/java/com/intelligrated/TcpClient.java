package com.intelligrated;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * simple tcp client
 */
public class TcpClient {
	static Socket socket;
	static PrintWriter output;
	static BufferedReader input;
	
	
	static String[] messages = {
		"hello from client",
		".... nice to know you",
		"foo",
		"man",
		"chu",
		"quit"
	};
	
	public static void main(String[] args) throws Exception {

		try {
			socket = new Socket("localhost", 4444);			
			
			output = new PrintWriter(socket.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			System.out.println("*** client starting");
			
			String response = "";
			for (String msgToServer : messages) {
				// send
				System.out.println("  msgToServer: " + msgToServer);
				output.println(msgToServer);
				
				// receive
				response = input.readLine();
				if(null == response || response.equals("QUIT")) {
					Thread.sleep(1000);
					break;
				}
				System.out.println("   client got back: " + response);
			}
			System.out.println("*** client stopping");

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}  finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("client FAILED to close connection");
			}
			
			output.close();
			input.close();
		}
		

	}

	// static Socket clientSocket;
	// static DataOutputStream output;
	// static BufferedReader input;
	//
	// public static void main(String[] args) throws IOException {
	// if (args.length != 1) {
	// System.err.println("Usage: java TcpClient <port number>");
	// System.exit(1);
	// }
	//
	// int portNumber = Integer.parseInt(args[0]);
	// System.out.println("client port: " + portNumber);
	//
	// BufferedReader inputFromConsole = new BufferedReader(new
	// InputStreamReader(System.in));
	//
	// System.out.println("client created socket");
	// clientSocket = new Socket("localhost", portNumber);
	//
	// output = new DataOutputStream(clientSocket.getOutputStream());
	//
	// input = new BufferedReader(new
	// InputStreamReader(clientSocket.getInputStream()));
	//
	// String sentence = inputFromConsole.readLine();
	//
	// output.writeBytes(sentence + '\n');
	//
	// String fromServer = input.readLine();
	//
	// System.out.println(" from server: " + fromServer);
	//
	// clientSocket.close();
	// }
}
