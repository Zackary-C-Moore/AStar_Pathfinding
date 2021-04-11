import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class RadioButtonActionListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JRadioButton radioButtonSelected = (JRadioButton)e.getSource();
		
		System.out.println(radioButtonSelected.getText());
		
	}

}
