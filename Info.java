import java.net.*;

public class Info{
	//ini

	private static Socket socket;
	private InetAddress ipAddress;
	private String remoteAddress;

	Info(Socket socket){
		//constructor

		// Get the socket from Chat Server.
		this.socket = socket;
	}

	public void addElement(){
		// Get an InetAddress instance from the socket.
		ipAddress = socket.getInetAddress();
		// Get the string representation of the IP address.
		remoteAddress = ipAddress.toString();
		// Add the IP address to the clients list.
		ClientList.addElement(remoteAddress);
	}
}