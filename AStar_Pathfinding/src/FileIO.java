import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileIO 
{
	public static void readRandomMaze() throws IOException
	{
		try 
		{
			
			FileReader input = new FileReader(new File("maze00.txt"));
			int text;
			int r = 0;
			int c = 0;
			while((text = input.read()) != -1)
			{
				//determine how many columns have been read
				if(c == AStar.getCols())
				{
					c = 0;
					r++;
				}
				//ignore the text if it is a new line or carriage return
				if(text != 10 && text != 13)
				{
					//load what we read into the array
					AStar.loadMazeIntoArray(r, c, (char)text);
					c++;
				}
				
			}
			AStar.displayBoard();
		} catch (FileNotFoundException e) 
		{
			System.out.println("Error: File not found");
		}
	}

}
