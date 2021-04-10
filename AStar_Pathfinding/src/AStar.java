import java.awt.Color;

//Zackary Moore

public class AStar 
{
	private static Node grid[][] = new Node[10][19];
	public static void main(String[] args) 
	{
		setupButtonsAndArray();
		grid[0][0].getNodeButton().setBackGroundColor(Color.red);
	}
	
	public static void setupButtonsAndArray()
	{
		GUI gui = new GUI();
		for(int r = 0; r < 10; r++)
		{
			for(int c = 0; c < 19; c++)
			{
				grid[r][c] = new Node();
				grid[r][c].setNodeButton(gui.addButtons());
			}
		}
		gui.showFrame();
	}

}
