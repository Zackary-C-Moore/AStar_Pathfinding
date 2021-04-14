import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class RadioButtonActionListener implements ActionListener
{
	GUI gui = new GUI();
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JRadioButton radioButtonSelected = (JRadioButton)e.getSource();
		String radioButtonName = radioButtonSelected.getName();
		
		if(radioButtonName.equals(gui.getRadioButtonNameWall()))
		{
			AStar.setCharacterColorToPlace(0);
		}
		else if(radioButtonName.equals(gui.getRadioButtonNameStart()))
		{
			AStar.setCharacterColorToPlace(1);
		}
		else if(radioButtonName.equals(gui.getRadioButtonNameEnd()))
		{
			AStar.setCharacterColorToPlace(2);
		}
		else
		{
			//error
			System.out.println("Error: RadioButtonActionHandler");
		}
		
	}

}
