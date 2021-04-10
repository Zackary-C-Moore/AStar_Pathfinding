import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
public class NodeButton extends JButton
{
	private Dimension buttonSize = new Dimension(100,100);
	private JButton button;
	private JLabel f;
	private JLabel g;
	private JLabel h;
	
	public NodeButton()
	{
		button = new JButton();
		
		f = new JLabel();
		g = new JLabel();
		h = new JLabel();
		
		f.setText("82");
		f.setBorder(null);
		
		g.setText("58");
		f.setBorder(null);
		
		h.setText("24");
		f.setBorder(null);
		
		setButtonDisplay(f.getText(), g.getText(), h.getText());
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
	public void setButtonDisplay(String f, String g, String h)
	{
		this.f.setText(f);
		this.g.setText(g);
		this.h.setText(h);
		String htmlForButton = "<html><table border = 0 style = 'font-size: 8px; horizontal-align:left'><tr><td>" + f + "</td><td></td><td>" + g +"</tr>"+
                "<tr><td></td><td>" + h + "</td><td></td></tr></table></html>";

		button.setText(htmlForButton);
	}
	public void setBackGroundColor(Color c)
	{
		button.setBackground(c);
	}
}
