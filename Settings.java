import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Settings extends JPanel implements ActionListener{
	// ini

	private static JFrame settingsFrame;
	private static String host = "localhost";
	private static String ip = "127.0.0.1";
	private static short port = 4444;
	private static String nickname = "Guest";
	private static JTextField hostname;
	private static JTextField ipAddress;
	private static JTextField portNumber;
	private static JTextField nickName;
	Settings(){
		// constructor
	}

	public void showWindow(JFrame window){
		// Set frame title.
		settingsFrame = new JFrame("Settings");
		// When frame is closed it hides but it is not destroyed.
		settingsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// The frame needs a container for JComponents.
		Container contentPane = settingsFrame.getContentPane();
		// Use the low-level pixel edge constraints layout manager.
		SpringLayout layout = new SpringLayout();
		// Set the layout manager to the JComponent container.
		contentPane.setLayout(layout);

		// Create a hostname text field. Set data source and font.
		hostname = new JTextField(this.host, 10);
		hostname.setFont(new Font("Arial", Font.BOLD, 15));

		// Create an IP address text field. Set data source and font.
		ipAddress = new JTextField(this.ip, 10);
		ipAddress.setFont(new Font("Arial", Font.BOLD, 15));

		// Convert port number to string literal.
		Short p = this.port; String pp = p.toString();

		// Create a port number text field. Set data source and font.
		portNumber = new JTextField(pp, 10);
		portNumber.setFont(new Font("Arial", Font.BOLD, 15));

		// Create a nickname text field. Set data source and font.
		nickName = new JTextField(nickname, 10);
		nickName.setFont(new Font("Arial", Font.BOLD, 15));

		// Create a hostname label. Set title and font.
		JLabel hostnameLabel = new JLabel("Hostname: ");
		hostnameLabel.setFont(new Font("Arial", Font.BOLD, 15));

		// Create an IP address lable. Set title and font.
		JLabel ipAddressLabel = new JLabel("IP: ");
		ipAddressLabel.setFont(new Font("Arial", Font.BOLD, 15));

		// Create a port number lable. Set title and font.
		JLabel portNumberLabel = new JLabel("Port: ");
		portNumberLabel.setFont(new Font("Arial", Font.BOLD, 15));

		// Create a nickname label. Set title and font.
		JLabel nickNameLabel = new JLabel("Nickname: ");
		nickNameLabel.setFont(new Font("Arial", Font.BOLD, 15));

		// Create a set settings button. Set title and font.
		JButton setButton = new JButton("Set");
		// Set action listener.
		setButton.addActionListener(this);
		setButton.setFont(new Font("Arial", Font.BOLD, 20));

		// Add the hostname components to the container.
		contentPane.add(hostnameLabel);
		contentPane.add(hostname);

		// Add the IP address components to the container.
		contentPane.add(ipAddressLabel);
		contentPane.add(ipAddress);

		// Add the port number components to the container.
		contentPane.add(portNumberLabel);
		contentPane.add(portNumber);

		// Add the nickname components to the container.
		contentPane.add(nickNameLabel);
		contentPane.add(nickName);

		// Add the set button component to the container.
		contentPane.add(setButton);

		// Set Host Name Label and Host Name Field constraints.
		layout.putConstraint(SpringLayout.NORTH, hostnameLabel, 10, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, hostnameLabel, 10, SpringLayout.WEST, contentPane);

		layout.putConstraint(SpringLayout.NORTH, hostname, 5, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, hostname, 100, SpringLayout.WEST, contentPane);

		// Set IP Address Label and IP Address Text Field constraints.
		layout.putConstraint(SpringLayout.NORTH, ipAddressLabel, 45, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, ipAddressLabel, 10, SpringLayout.WEST, contentPane);

		layout.putConstraint(SpringLayout.NORTH, ipAddress, 40, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, ipAddress, 100, SpringLayout.WEST, contentPane);

		// Set Port Number Label and Port Number Text Field constraints.
		layout.putConstraint(SpringLayout.NORTH, portNumberLabel, 80, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, portNumberLabel, 10, SpringLayout.WEST, contentPane);

		layout.putConstraint(SpringLayout.NORTH, portNumber, 75, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, portNumber, 100, SpringLayout.WEST, contentPane);

		// Set Nickname Label and Nickname Text Field constraints.
		layout.putConstraint(SpringLayout.NORTH, nickNameLabel, 115, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, nickNameLabel, 10, SpringLayout.WEST, contentPane);

		layout.putConstraint(SpringLayout.NORTH, nickName, 110, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, nickName, 100, SpringLayout.WEST, contentPane);

		// Set Button constraints.
		layout.putConstraint(SpringLayout.NORTH, setButton, 145, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, setButton, 0, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.EAST, setButton, 0, SpringLayout.EAST, contentPane);
		//layout.putConstraint(SpringLayout.SOUTH, setButton, 1, SpringLayout.SOUTH, contentPane);

		// The new frame should appear inside the main window frame.
		settingsFrame.setLocationRelativeTo(window);
		//Display the window. 
		settingsFrame.pack();
		// Set the default size of the window.
		settingsFrame.setSize(new Dimension(250, 210));
		// Set the window so that the user cannot resise it.
		settingsFrame.setResizable(false);
		// Show window.
		settingsFrame.setVisible(true);
	}

	// If the set settings button has been clicked.
	public void actionPerformed(ActionEvent e) {
		// If none of the fields are empty.
		if(!hostname.getText().isEmpty() && !ipAddress.getText().isEmpty() && !portNumber.getText().isEmpty()  && !nickName.getText().isEmpty()){
			
			// Set the host, ip, port and nickname settings.
			this.host = hostname.getText();
			this.ip = ipAddress.getText();
			this.port = Short.parseShort(portNumber.getText());
			this.nickname = nickName.getText();

			// Debug client info.
			System.out.println(host);
			System.out.println(ip);
			System.out.println(port);
			System.out.println(nickname);

		} else {
			JOptionPane.showMessageDialog(settingsFrame, "One or more empty fields!", "Chat Client Settings", 1);
		}
	}
	
	/*
	public static void setHost(){
		this.host = hostname.getText();
	}

	public static void setIP(){
		this.ip = 
	}

	public static void setPort(){
		this.port = 
	}

	public static void setNickname(){
		this.nickname = 
	}
	*/
	
	// Return host name.
	public static String getHost(){
		return host;
	}

	// Return IP address.
	public static String getIP(){
		return ip;
	}

	// Return port number.
	public static short getPort(){
		return port;
	}

	// Return nickname.
	public static String getNickname(){
		return nickname;
	}
}