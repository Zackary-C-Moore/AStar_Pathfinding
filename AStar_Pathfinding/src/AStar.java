import java.awt.Color;

//Zackary Moore

public class AStar 
{
	private static Node grid[][] = new Node[10][19];
	public static void main(String[] args) 
	{
		setupButtonsAndArray();
		grid[7][15].getNodeButton().setBackGroundColor(Color.red);
		grid[9][0].setFValue(100);
		grid[0][0].setGValue(25);
		grid[0][0].setHValue(300);
		//This could also work to get the button to display the correct h value
		//But I think it is easier to have .setHValue() in the Node class call
		//setHDisplay in the NodeButton class.  The logic is, every time I wan to 
		//change a nodes value the display should be changed as well.
		//grid[0][0].getNodeButton().setHDisplay(String.valueOf(grid[0][0].getHValue()));
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
