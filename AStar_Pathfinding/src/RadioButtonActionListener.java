import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class RadioButtonActionListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JRadioButton radioButtonSelected = (JRadioButton)e.getSource();
		String radioButtonName = radioButtonSelected.getName();
		
		//wall
		if(radioButtonName.equals(GUI.getRadioButtonNameWall()))
		{
			AStar.setCharacterColorToPlace(Details.getWallCharacter(), Details.getWallColor());
		}
		//start
		else if(radioButtonName.equals(GUI.getRadioButtonNameStart()))
		{
			AStar.setCharacterColorToPlace(Details.getStartCharacter(), Details.getStartColor());
		}
		//end
		else if(radioButtonName.equals(GUI.getRadioButtonNameEnd()))
		{
			AStar.setCharacterColorToPlace(Details.getEndCharacter(), Details.getEndColor());
		}
		else
		{
			//error
			System.out.println("Error: RadioButtonActionHandler");
		}
		
	}

}
