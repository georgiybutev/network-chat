import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.UIManager.*;

public class ChatClient{

	// For safety this method is invoked from the event dispatching thread.
	private static void createAndShowGUI(){
		// Java decoration look.
		JFrame.setDefaultLookAndFeelDecorated(false);
		// Create the top level.
		JFrame frame = new JFrame("Chat Client");
		// Create option for windows exit.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Center the application upon start-up.
		frame.setLocationRelativeTo(null);
		// Create and add menu bar and items.
		Menu menu = new Menu();
		frame.setJMenuBar(menu.createMenuBar(frame));
		// Create and add text area, text field, and send button.
		Layout layout = new Layout();
		frame.add(layout);
		// Package all components.
		frame.pack();
		// Set the default size of the window.
		frame.setSize(new Dimension(600, 400));
		// Set the window so that the user cannot resise it.
		frame.setResizable(false);
		// Show window.
		frame.setVisible(true);
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
		
		// Run the thread to create the GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {public void run() {createAndShowGUI();}});
	}
}