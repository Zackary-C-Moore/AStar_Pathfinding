import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class NodeButton extends JButton
{
	private Dimension buttonSize = new Dimension(100,100);
	private JButton button;
	private JLabel fLabel;
	private JLabel gLabel;
	private JLabel hLabel;
	
	public NodeButton()
	{
		button = new JButton();
		
		
		fLabel = new JLabel();
		gLabel = new JLabel();
		hLabel = new JLabel();
		
		//no border around selected button
		button.setFocusPainted(false);
		
		
	}
	
	public JButton getButton()
	{
		return button;
	}
	
	public Dimension getButtonSize()
	{
		return buttonSize;
	}

	//Used to setup the labels to display on the buttons.
	public void setButtonDisplay()
	{
		String f = this.fLabel.getText();
		String g = this.gLabel.getText();
		String h = this.hLabel.getText();
		
		String htmlForButton = "<html><table border = 0 style = 'font-size: 8px; horizontal-align:left'><tr><td>" + f + "</td><td></td><td>" + g +"</tr>"+
                "<tr><td></td><td>" + h + "</td><td></td></tr></table></html>";

		button.setText(htmlForButton);
	}
	
	//Called from Node when we edit the f value
	public void setFDisplay(String f)
	{
		this.fLabel.setText(f);
		setButtonDisplay();
	}
	//Called from Node when we edit the g value
	public void setGDisplay(String g)
	{
		this.gLabel.setText(g);
		setButtonDisplay();
	}
	//Called from Node when we edit the h value
	public void setHDisplay(String h)
	{
		this.hLabel.setText(h);
		setButtonDisplay();
	}
	public void setBackGroundColor(Color c)
	{
		button.setBackground(c);
	}
	public void setName(int r, int c)
	{
		button.setName(r + "," + c);
	}
}
