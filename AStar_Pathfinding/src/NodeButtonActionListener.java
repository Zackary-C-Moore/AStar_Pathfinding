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
		//buttonPressed.setBackground(Color.blue);
		
		//Used to parse through the name to figure out which row and column need to be changed
		//I used a basic naming standard when creating the buttons (r,c)
		getRowCol(buttonPressed.getName());
	}
	
	//parse through the name to get row and column position
	public void getRowCol(String name)
	{
		int commaPos = name.indexOf(',');
		//read up until the comma.  that is the row.  Convert to int
		int r = Integer.parseInt(name.substring(0,commaPos));
		//read everything after the comma.  that is the col.  Convert to int
		int c = Integer.parseInt(name.substring(commaPos + 1));
		
		//call AStar and update the 2d array to match the GUI.
		AStar.updateBoardAfterClickEvent(r,c);
	}

}
