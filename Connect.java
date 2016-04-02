import java.io.*;
import java.net.*;
import javax.net.*;
import javax.net.ssl.*;

public class Connect{
	// ini

	private String host;
	private String address;
	private short port;
	private String nickname;
	private static Boolean hasConnected;

	public SSLSocketFactory sslFactory;
	public static SSLSocket sslSocket;
	private InputStreamReader inputStream;
	private static BufferedReader incomingData;
	private static PrintWriter outgoingData;

	private static InetAddress ipAddress;
	private static String localAddress;

	public Connect(){
		// constructor

		// Get host, address, port, nickname from the settings class.
		this.host = Settings.getHost();
		this.address = Settings.getIP();
		this.port = Settings.getPort();
		this.nickname = Settings.getNickname();
		// The client has not connected to the server yet.
		this.hasConnected = false;
	}

	public void establishConnection(){
		// socket
		try{
			// Construct the client SSLSocket with host and port.
			sslFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			sslSocket = (SSLSocket) sslFactory.createSocket(host, port);
			// Get SSLSocket input stream.
			incomingData = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
			// Get SSLSocket output stream.
			outgoingData = new PrintWriter(sslSocket.getOutputStream(), true);
		} catch (IOException e) {
			// The client has not connected to the server yet.
			hasConnected = false;
			System.err.println(e);
		}
		// The client has connected to the server.
		hasConnected = true;
		// Display message to the client.
		Layout.showMessage("Now connected as " + nickname + ".\n");
		// Start and run a new thread to listen for incoming data.
		new Thread(new Runnable(){public void run() {listenServer();}}).start();
	}

	// Return true or false whether the client is connected to the server.
	public static Boolean getHasConnected(){
		return hasConnected;
	}

	// Return the SSLSocket.
	public static SSLSocket getSSLSocket(){
		return sslSocket;
	}

	// Return the incoming data.
	public static BufferedReader getIncomingData(){
		return incomingData;
	}

	// Return the outgoing data.
	public static PrintWriter getOutgoingData(){
		return outgoingData;
	}

	// Liste for incoming data.
	public static void listenServer(){
		while(true){
			String in = null;
			
			try{

				// Read a line from the stream.
				in = incomingData.readLine();
				// Show the message to the user.
				Layout.showMessage(in + "\n");

				// Listen for the disconnect command.
				if(in.startsWith("!#disconnect")){
					// Get the local IP address.
					ipAddress = sslSocket.getLocalAddress();
					// Get the string representation of the InetAddress.
					localAddress = ipAddress.toString();
					// Client debug information.
					System.out.println(localAddress);
					System.out.println(in.substring(12));
					// If the local address matches the server disconnect command combo.
					if(localAddress.equalsIgnoreCase(in.substring(12))){
						// Client debug information.
						System.out.println("YES");
						// Show the message to the user.
						Layout.showMessage("You have been disconnected!" + "\n");
						Disconnect disconnect = new Disconnect(sslSocket, incomingData, outgoingData);
						// Disconnect from the server.
						disconnect.disruptConnection();
					}
				}

			} catch(Exception ioe){
				Layout.showMessage("IO Error!\n");
				break;
			}
		}
	}
}