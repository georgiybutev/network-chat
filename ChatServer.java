import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.*;
import java.io.*;
import java.net.*;
import javax.net.*;
import javax.net.ssl.*;
import java.util.*;

public class ChatServer extends JPanel{

	private static JList list;
	private static JButton disconnectButton;
	private static JScrollPane scroll;
	private static ServerSocket server;
	
	public static PrintWriter output;
	public static SSLServerSocketFactory sslFactory;
	public static SSLServerSocket sslServer;
	public static SSLSocket sslSocket;
	public static ArrayList arrayList = new ArrayList();

	private static int numOfClients;
	private static JProgressBar load;
	private static int portNumber = 4444;
	private static JFrame frame;
	private static String info;
	
	// For safety this method is invoked from the event dispatching thread.
	private static void startServer(){
		// Get the server port number from user input.
		String port = JOptionPane.showInputDialog(null, "Enter server port number: ", "Chat Server", 1);
		// Parse the string into a 2^16 digit variable.
		portNumber = Integer.parseInt(port);

		// Java decoration look.
		JFrame.setDefaultLookAndFeelDecorated(false);
		// Create the top level.
		frame = new JFrame("Chat Server");
		// Create option for windows exit.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Center the application upon start-up.
		frame.setLocationRelativeTo(null);
		// Create and set JComponents layout maneger.
		SpringLayout layout = new SpringLayout();
		frame.setLayout(layout);

		// Create an object to deal with the chat clients list.
		ClientList clientList = new ClientList(frame);
		// Get the JList component.
		list = ClientList.getList();
		// Add scroll bar (horizonal) for many clients.
		scroll = new JScrollPane(list);
		// Add the scroll bar to the frame.
		frame.add(scroll);

		// Adjust the position of the client list using N, W, S, E directions.
		layout.putConstraint(SpringLayout.NORTH, scroll, 5, SpringLayout.NORTH, frame);
		layout.putConstraint(SpringLayout.WEST, scroll, 5, SpringLayout.WEST, frame);
		layout.putConstraint(SpringLayout.SOUTH, scroll, 100, SpringLayout.SOUTH, frame);
		layout.putConstraint(SpringLayout.EAST, scroll, 285, SpringLayout.EAST, frame);

		// Create, add, and adjust the position of the MIN lable to the frame.
		JLabel min = new JLabel("MIN");
		frame.add(min);
		layout.putConstraint(SpringLayout.NORTH, min, 125, SpringLayout.NORTH, scroll);
		layout.putConstraint(SpringLayout.WEST, min, 7, SpringLayout.WEST, frame);

		// Create, add, and adjust the position of the MAX lable to the frame.
		JLabel max = new JLabel("MAX");
		frame.add(max);
		layout.putConstraint(SpringLayout.NORTH, max, 125, SpringLayout.NORTH, scroll);
		layout.putConstraint(SpringLayout.WEST, max, 260, SpringLayout.WEST, frame);

		// Create a progress bar on the frame.
		// The range is from 0 to 100 or 101 clients.
		load = new JProgressBar(0, 100);
		// Set the value using the number of clients variable counter.
		load.setValue(numOfClients);
		// Set the progress bar visuals.
		load.setBorderPainted(true);
		load.setStringPainted(true);
		// Add and position the progress bar on the frame.
		frame.add(load);
		layout.putConstraint(SpringLayout.NORTH, load, 125, SpringLayout.NORTH, scroll);
		layout.putConstraint(SpringLayout.WEST, load, 75, SpringLayout.WEST, frame);

		// Create, add, and position the disconnect button on the frame.
		disconnectButton = ClientList.getButton();
		frame.add(disconnectButton);
		layout.putConstraint(SpringLayout.NORTH, disconnectButton, 145, SpringLayout.NORTH, scroll);
		layout.putConstraint(SpringLayout.WEST, disconnectButton, 5, SpringLayout.WEST, frame);
		layout.putConstraint(SpringLayout.EAST, disconnectButton, 285, SpringLayout.EAST, frame);

		// Package all components.
		frame.pack();
		// Set the default size of the window.
		frame.setSize(new Dimension(300, 220));
		// Set the window so that the user cannot resise it.
		frame.setResizable(false);
		// Show window.
		frame.setVisible(true);
	}

	// Accept new clients to server using TCP socket.
	public static void acceptNew(){
		try{

			// Construct SSLSocket server.
			sslFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			sslServer = (SSLServerSocket) sslFactory.createServerSocket(portNumber);

			while(true){
				// Accept new clients through the SSLSocket.
				sslSocket = (SSLSocket) sslServer.accept();

				// Enable Nagle's algorithm
				try{
					sslSocket.setTcpNoDelay(false);
				}catch(SocketException e){
					System.err.println(e);
				}

				// Each client will use a thread with an SSLSocket instance.
				MultiThread thread = new MultiThread(sslSocket, frame);
				// Start the thread immediately after the creation of the thread.
				thread.start();

				// Create an info object to get socket related information.
				Info info = new Info(sslSocket);
				// Add the socket information to the client list (i.e. InerAddress).
				info.addElement();
				
				// Create and add an instance of PrintWriter to the arrayList for each new client.
				arrayList.add(new PrintWriter(sslSocket.getOutputStream(), true));
				
				// Get the number of clients.
				// Equals the number of active threads minus 5 associated with the Swing components.
				numOfClients = thread.activeCount() - 5;
				// Set the number of clients on the progress bar.
				load.setValue(numOfClients);
			}
		}catch(IOException ioe){
			JOptionPane.showMessageDialog(frame, "Could not open the socket.", "Chat Server", 0);
		}
	}

	// This is the event dispatching thread.
	public static void main(String[] args){
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
		
		// Run start server thread.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {public void run() {startServer();}});
		// Run start accept new clients thread.
		new Thread(new Runnable(){public void run() {acceptNew();}}).start();
	}
}