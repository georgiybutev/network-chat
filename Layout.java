import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Layout extends JPanel implements ActionListener {
	// ini

	protected JTextField textField;
	private static JTextArea textArea;
	protected JButton button;
	private Boolean hasConnected;
	private final static String newline = "\n";

	public Layout() {
		//constructor

		// Use the low-level pixel edge constraints layout manager.
		SpringLayout layout = new SpringLayout();
		// Set the layout manager to the JComponent container.
		setLayout(layout);

		// Communication between server and client area.
		textArea = new JTextArea(17, 52);
		// The user cannot edit the sent and received text.
		textArea.setEditable(false);
		// Enable scroll bar.
		JScrollPane scrollPane = new JScrollPane(textArea);

		add(scrollPane);

		// Text field for message user input.
		textField = new JTextField(30);
		// Set action listener.
		textField.addActionListener(this);
		// Set the font and size.
		textField.setFont(new Font("Arial", Font.BOLD, 20));

		add(textField);

		// Button to send a message.
		button = new JButton("Send");
		// Set the font and size.
		button.setFont(new Font("Arial", Font.BOLD, 20));
		// Set action listener.
		button.addActionListener(this);

		add(button);

		// Set text field and button constraints.
		layout.putConstraint(SpringLayout.NORTH, textField, 300, SpringLayout.NORTH, textArea);
		layout.putConstraint(SpringLayout.NORTH, button, 300, SpringLayout.NORTH, textArea);
		layout.putConstraint(SpringLayout.WEST, button, 515, SpringLayout.WEST, textField);
	}

	// If the send button has been clicked.
	public void actionPerformed(ActionEvent e){
		String in = null;
		
		// Get the client nickname.
		String nickname = Settings.getNickname();
		// Store and send message.
		String out = textField.getText();
		Connect.getOutgoingData().println(nickname + ": " + out);

		// Highligh the send message.
		textField.selectAll();
		// The text will be visible even if there is a selection.
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}

	// Append messages from other classes to the text area.
	public static void showMessage(String message){
		textArea.append(message);
	}
}