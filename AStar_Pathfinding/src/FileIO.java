import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class FileIO 
{
	private static int numberOfMazes;
	private static int currentMaze;
	public static void randomMazeSetup() throws IOException
	{
		readNumMazes();
		readRandomMaze();
	}
	public static void saveMazeSetup() throws IOException
	{
		readNumMazes();
		//write a file
		writeMaze();
		//increment number of mazes and save it
		writeNumMazes();
	}
	//Write the maze we just made to a text file
	public static void writeMaze()
	{
		try 
		{
			//concatenate to make a new unique text file name
			//do not increment numberOfMazes here because my naming and counting are off by one (started naming at 0)
			FileWriter writer = new FileWriter(new File("maze" + numberOfMazes + ".txt"));
			for(int r = 0; r < AStar.getRows(); r++)
			{
				for(int c = 0; c < AStar.getCols(); c++)
				{
					//-----this might need some additional work when if I let the user save mazes
					//-----that are solved (maybe no erasing the maze)
					//write the value of the array at r c to the text file
					writer.write(AStar.getNodeFromArray(r, c).getValue());
					//I want to clear the board once the user saved their maze.
					AStar.clearNode(AStar.getNodeFromArray(r, c));
				}
				writer.write("\n");
			}
			writer.flush();
			writer.close();
		} 
		catch (IOException e) 
		{
			System.out.println("Error: Writing new maze");
		}
	}
	//write to this file when we create a new maze so we know how many mazes we have to pick from
	public static void writeNumMazes()
	{
		try 
		{
			int newMazeNumber = numberOfMazes + 1;
			FileWriter writer = new FileWriter(new File("numberOfMazes.txt"));
			writer.write(String.valueOf(newMazeNumber));
			writer.flush();
			writer.close();
		} 
		catch (IOException e) 
		{
			System.out.println("Error: Writing number of mazes");
		}
	}
	//read how many mazes have been created so I can pick a random number to pick a random maze
	//I need to know how many mazes so I can pick a valid random number
	public static void readNumMazes() throws IOException
	{
		try 
		{
			Scanner input = new Scanner(new File("numberOfMazes.txt"));
			numberOfMazes = input.nextInt();

			input.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Error: File not found " + "num mazes");
		}
	}
	//Read the maze from the text file
	public static void readRandomMaze() throws IOException
	{
		try 
		{
			int randomMaze = getRandomIndexForMaze();
			//used to make sure I do not get the same maze two times in a row
			currentMaze = randomMaze;
			FileReader input = new FileReader(new File("maze" + randomMaze + ".txt"));
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
					//load what we read into the array in AStar
					AStar.loadMazeIntoArray(r, c, (char)text);
					c++;
				}
				
			}
			input.close();
			AStar.displayBoard();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Error: File not found");
		}
		
	}
	
	public static int getRandomIndexForMaze()
	{
		Random rand = new Random();
		int upperBound = numberOfMazes;
		
		int randomMaze = rand.nextInt(upperBound);
		//make sure I do not get the same maze two times in a row
		while(randomMaze == currentMaze)
		{
			randomMaze = rand.nextInt(upperBound);
		}
		
		return randomMaze;
		
	}

}
