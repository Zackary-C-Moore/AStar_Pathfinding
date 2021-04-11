import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class NodeButtonActionListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//Figure out which button was pressed
		JButton buttonPressed = (JButton)e.getSource();
		//Testing to see if I can change the color of the button.
		buttonPressed.setBackground(Color.red);
	}

}
