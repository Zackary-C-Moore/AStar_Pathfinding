import java.awt.Color;

//Zackary Moore

public class AStar 
{
	private static Node grid[][] = new Node[10][19];
	private static NodeButton nb = new NodeButton();
	public static void main(String[] args) 
	{
		setupButtonsAndArray();
		grid[7][15].getNodeButton().setBackGroundColor(Color.red);
		grid[9][0].setFValue(100);
		grid[0][0].setGValue(25);
		grid[0][0].setHValue(300);
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
