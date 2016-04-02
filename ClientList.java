import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ClientList extends JPanel implements ListSelectionListener, ActionListener{
	//ini
	private static JFrame frame;
	private static JList list;
	private static JButton button;
	private static DefaultListModel model;
	private static JScrollPane scroll;

	public ClientList(JFrame frame){
		//constructor

		// Get the ChatServer frame.
		this.frame = frame;
		// Create a list model.
		model = new DefaultListModel();
		// Add the first element as a welcome message.
		model.addElement("Welcome Georgi!");

		// Create the clients list based on the model.
		list = new JList(model);
		// Multiple clients selection is disallowed.
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// The first element is selected on startup.
		list.setSelectedIndex(0);
		// Listen for client list events (i.e. select item).
		list.addListSelectionListener(this);

		// Create the disconnect button.
		button = new JButton("Disconnet Client");
		// Set the font and size.
		button.setFont(new Font("Arial", Font.BOLD, 20));
		// Set action listener.
		button.addActionListener(this);
	}

	// Return the list to ChatServer.
	public static JList getList() {
		return list;
	}

	// Return the disconnect button to ChatServer.
	public static JButton getButton(){
		return button;
	}

	// Get message from client that was destined for the server.
	public static void addElement(String toServer){
		model.addElement(toServer);
	}

	public void valueChanged(ListSelectionEvent e){
		// Mandatory method for List Selection Listener
	}

	// Get selected index, value, and send disconnect command.
	public void actionPerformed(ActionEvent e){
		int selectedIndex = list.getSelectedIndex();
		String selectedValue = (String) list.getSelectedValue();

		try{
			// Iterate over the array list for each PrintWriter.
			Iterator iterator = ChatServer.arrayList.iterator();
			// As long as there is a next element, send disconnect command.
			while(iterator.hasNext()){
				PrintWriter out = (PrintWriter) iterator.next();
				out.println("!#disconnect" + selectedValue);
			}
		} catch (Exception ee){
			JOptionPane.showMessageDialog(frame, "Could not write", "Chat Server", 1);
		}

		// Chat Server debug information.
		System.out.println("selected index " + selectedIndex);
		System.out.println("selected value " + selectedValue);

		// Remove the selected index.
		// This is the disconnected client.
		model.remove(selectedIndex);
	}
}