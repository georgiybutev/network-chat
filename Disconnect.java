import java.io.*;
import javax.net.ssl.SSLSocket;

public class Disconnect{
	// ini

	private SSLSocket sslSocket;
	private BufferedReader incomingData;
	private PrintWriter outgoingData;

	public Disconnect(SSLSocket sslSocket, BufferedReader incomingData, PrintWriter outgoingData){
		// constructor

		// Get the SSLSocket from Connect class.
		this.sslSocket = sslSocket;
		// Get the input stream from Connect class.
		this.incomingData = incomingData;
		// Get the output SSLSocket from Connect class.
		this.outgoingData = outgoingData;
	}

	public void disruptConnection(){
		try{
			// Close the input and output stream.
			incomingData.close();
			outgoingData.close();
			// Close the SSLSocket.
			sslSocket.close();
		} catch(IOException e) {
			System.err.println(e);
		}
	}
}