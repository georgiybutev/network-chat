import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.*;
import java.io.*;
import java.net.*;
import javax.net.ssl.*;
import java.util.*;

public class MultiThread extends Thread{
	//ini

	public static SSLSocket sslSocket;
	private static InputStreamReader isr;
	private static BufferedReader input;
	public String temp;
	private JFrame frame;
	private Message message;

	MultiThread(SSLSocket sslSocket, JFrame frame){
		//constructor

		// Try to setup the Java Nimbus look and feel.
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Please install the latest Java JRE!");
		}

		// Get the SSLSocket from Chat Server.
		this.sslSocket = sslSocket;
		// Get the frame from Chat Server.
		this.frame = frame;
		// Create a message object for the Chat Server log.
		message = new Message();
	}

	public void run(){
		while(true){

			// Read from the socket stream.
			read();

			// If the client message starts with toserver.
			if (temp.startsWith("toserver")){
				// Get the toserver message which is after the 8th character.
				String toServer = temp.substring(8);
				// Get message from client that was destined for the server.
				ClientList.addElement(toServer);
			} else {
				// Chat Server debug info.
				System.out.println("From user: " + temp);
				// Log the client message.
				message.log(temp);

				// Write to socket stream.
				write();
			}
		}
	}

	public void read(){
		try{
			// Get the socket stream.
			isr = new InputStreamReader(sslSocket.getInputStream());
			// Buffer read it for better efficiency.
			input = new BufferedReader(isr);
			// Read a line from the buffer.
			temp = input.readLine();
		}catch (IOException e){
			JOptionPane.showMessageDialog(frame, "Could not read", "Chat Server", 1);
		}
	}

	public void write(){
		try{
			// Iterate over the array list for each PrintWriter.
			Iterator iterator = ChatServer.arrayList.iterator();
			// As long as there is a next element, send disconnect command.
			while(iterator.hasNext()){
				PrintWriter out = (PrintWriter) iterator.next();
				out.println(temp);
			}
		} catch (Exception e){
			JOptionPane.showMessageDialog(frame, "Could not write", "Chat Server", 1);
		}
	}
}