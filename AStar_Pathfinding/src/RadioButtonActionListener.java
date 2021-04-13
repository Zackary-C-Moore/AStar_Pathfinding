import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class RadioButtonActionListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JRadioButton radioButtonSelected = (JRadioButton)e.getSource();
		
		if(radioButtonSelected.getText().equals("Wall"))
		{
			AStar.setCharacterColorToPlace(0);
		}
		else if(radioButtonSelected.getText().equals("Start"))
		{
			AStar.setCharacterColorToPlace(1);
		}
		else
		{
			AStar.setCharacterColorToPlace(2);
		}
		
	}

}
