import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

public class ClientGUI extends JPanel implements ActionListener {
	
	protected JTextField textInput;
	protected JTextArea communication;
	protected JButton sendMsg;

	public ClientGUI() {
		super(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// Button to send messages.
		sendMsg = new JButton("Send");
		// Set the font type and weigth.
		sendMsg.setFont(new Font("Arial", Font.BOLD, 20));
		// Set action listener.
		sendMsg.addActionListener(this);
		//sendMsg.setActionCommand("sendMsg");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 1;
		// Allign button to the beggining of the line.
		add(sendMsg, c);

		// Text field for message user input.
		textInput = new JTextField("Send a message ...");
		// Set the font to Arial.
		textInput.setFont(new Font("Arial", Font.BOLD, 20));
		// Adjust text input size.
		textInput.addActionListener(this);
		//textInput.setActionCommand("textInput");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.gridx = 1;
		c.gridy = 1;
		// Align to top of the frame.
		add(textInput, c);

		// Communication between server and client area.
		communication = new JTextArea();
		// The user cannot edit the sent and received text.
		communication.setEditable(false);
		// Enable scroll bar.
		JScrollPane scroll = new JScrollPane(communication);
		// Align to end of the line.
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 270;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		add(scroll, c);

	}
	
	public void actionPerformed(ActionEvent e) {
		String text = textInput.getText();
		communication.append(text);
		textInput.selectAll();
		//communication.setCaretPosition(communication.getDocument().getLength());
	}
}