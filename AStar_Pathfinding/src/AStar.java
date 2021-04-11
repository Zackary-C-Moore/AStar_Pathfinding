import java.awt.Color;

//Zackary Moore

public class AStar
{
	private static Node grid[][] = new Node[10][19];
	public static void main(String[] args) 
	{
		setupButtonsAndArray();
		//You can do either way but the first way requires that I create a setBackGroundColor() function in NodeButton
		grid[7][15].getNodeButton().setBackGroundColor(Color.red);
		//grid[7][15].getNodeButton().getButton().setBackground(Color.red);
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
				//You can do either way but the first way requires that I create a setName() function in NodeButton
				grid[r][c].getNodeButton().setName(r,c);
				//grid[r][c].getNodeButton().getButton().setName(r + "," + c);
				grid[r][c].getNodeButton().getButton().addActionListener(new NodeButtonActionListener());
			}
		}
		gui.showFrame();
	}
	
	public static void updateBoardAfterClickEvent(int r, int c)
	{
		grid[r][c].setFValue(999);
		grid[r][c].setValue('W');
		displayBoard();
	}
	//Debugging purposes so I can make sure the GUI and the backend are identical.
	public static void displayBoard()
	{
		for(int r = 0; r < 10; r++)
		{
			for(int c = 0; c < 19; c++)
			{
				System.out.print(grid[r][c].getValue());
			}
			System.out.println();
		}
	}

}
