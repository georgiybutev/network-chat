import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu implements ActionListener{
	// ini

	protected JFrame window;

	public JMenuBar createMenuBar(JFrame frame){
		//constructor

		// Get the frame from Chat Client.
		window = frame;
		// Initialise the menu components.
		JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem;
		// Create the menu bar.
		menuBar = new JMenuBar();
		// Build the File menu.
		menu = new JMenu("Network");
		menu.getAccessibleContext().setAccessibleDescription("Managage network connection.");
		menuBar.add(menu);
		// JMenu Item Connect to server.
		ImageIcon connectIcon = loadIcon("images/connect.png");
		menuItem = new JMenuItem("Connect", connectIcon);
		menuItem.addActionListener(this);
		menuItem.setActionCommand("Connect");
		menu.add(menuItem);
		// JMenu Item Disconnect from server.
		ImageIcon disconnectIcon = loadIcon("images/disconnect.png");
		menuItem = new JMenuItem("Disconnect", disconnectIcon);
		menuItem.addActionListener(this);
		menuItem.setActionCommand("Disconnect");
		menu.add(menuItem);
		// JMenu Item Settings - IP/hostname and port number.
		ImageIcon settingsIcon = loadIcon("images/cog.png");
		menuItem = new JMenuItem("Settings", settingsIcon);
		menuItem.addActionListener(this);
		menu.add(menuItem);
		// Build the About menu.
		menu = new JMenu("About");
		menuBar.add(menu);
		// JMenu Item Developer.
		ImageIcon developerIcon = loadIcon("images/java.png");
		menuItem = new JMenuItem("Developer", developerIcon);
		menuItem.addActionListener(this);
		menu.add(menuItem);
		// JMenu Item Version.
		ImageIcon aboutIcon = loadIcon("images/information.png");
		menuItem = new JMenuItem("Version", aboutIcon);
		menuItem.addActionListener(this);
		menu.add(menuItem);
		// Return the menu bar to Chat Client.
		return menuBar;
	}

	// Respond to menu bar button actions.
	public void actionPerformed(ActionEvent e){
		// If connect menu item has been selected.
		if("Connect".equals(e.getActionCommand())){
			Connect connect = new Connect();
			// Connect to the server.
			connect.establishConnection();
		// If disconnect menu item has been selected.
		} else if("Disconnect".equals(e.getActionCommand())){
			// Make sure the client is actually connected to the server.
			if(Connect.getHasConnected()){
				Disconnect disconnect = new Disconnect(Connect.getSSLSocket(), Connect.getIncomingData(), Connect.getOutgoingData());
				// Disconnect from the server.
				disconnect.disruptConnection();
			} else {
				JOptionPane.showMessageDialog(window, "You are not connected to the server!", "Connection", 1);
			}
		// If settings menu item has been selected.
		} else if("Settings".equals(e.getActionCommand())){
			Settings settings = new Settings();
			// Show the settings window.
			settings.showWindow(window);
		// If developer menu item has been selected.
		} else if("Developer".equals(e.getActionCommand())){
			JOptionPane.showMessageDialog(window, "This simple chat application was developed by:\nGeorgi Butev\nbutevg@lsbu.ac.uk","Version", 1);
		// If version menu item has been selected.
		} else if("Version".equals(e.getActionCommand())){
			JOptionPane.showMessageDialog(window, "1.0 Alpha", "Version", 1);
		}
	}

	// This method is used to load an image icon using the path argument.
	protected static ImageIcon loadIcon(String path){
		java.net.URL imgURL = ChatClient.class.getResource(path);
		// If the image path is correct.
		if(imgURL != null){
			return new ImageIcon(imgURL);
		// If the image path is incorrect.
		} else {
			// 0 means error message.
			JOptionPane.showMessageDialog(null, "Could not load icon!", "Error", 0);
			return null;
		}
	}
}