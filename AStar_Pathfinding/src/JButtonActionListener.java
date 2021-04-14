import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JButtonActionListener implements ActionListener
{
	GUI gui = new GUI();
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton buttonPressed = (JButton)e.getSource();
		String buttonName = buttonPressed.getName();
		
		if(buttonName.equals(gui.getJButtonNameClear()))
		{
			AStar.resetBoard();
		}
		else if(buttonName.equals(gui.getJButtonNameFindPath()))
		{
			//ai to solve for path
		}
		else if(buttonName.equals(gui.getJButtonNameNextStep()))
		{
			//One step forward in the solution
		}
		else if(buttonName.equals(gui.getJButtonNameRandomMaze()))
		{
			//generate a random maze
		}
		else
		{
			//error
			System.out.println("Error: JButtonActionListener");
		}
		
	}

}
