import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

public class JButtonActionListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton buttonPressed = (JButton)e.getSource();
		String buttonName = buttonPressed.getName();
		
		if(buttonName.equals(GUI.getJButtonNameClear()))
		{
			AStar.resetBoard();
		}
		else if(buttonName.equals(GUI.getJButtonNameFindPath()))
		{
			//ai to solve for path
		}
		else if(buttonName.equals(GUI.getJButtonNameNextStep()))
		{
			//One step forward in the solution
		}
		else if(buttonName.equals(GUI.getJButtonNameRandomMaze()))
		{
			try 
			{
				FileIO.randomMazeSetup();
			} 
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(buttonName.equals(GUI.getJButtonNameSaveMaze()))
		{
			//write the current board state to a file
			try 
			{
				FileIO.saveMazeSetup();
			} 
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else
		{
			//error
			System.out.println("Error: JButtonActionListener");
		}
		
	}

}
