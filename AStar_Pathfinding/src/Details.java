import java.awt.Color;

public class Details 
{
	private static final int numRows = 12;
	private static final int numCols = 23;
	private static final char openCharacter = '-';
	private static final char wallCharacter = 'W';
	private static final char startCharacter = 'S';
	private static final char endCharacter = 'E';
	private static final Color openColor = Color.gray;
	private static final Color wallColor = Color.black;
	private static final Color startColor = Color.cyan;
	private static final Color endColor = Color.magenta;
	
	
	//Getters
	public static int getNumRows()
	{
		return numRows;
	}
	public static int getNumCols()
	{
		return numCols;
	}
	public static char getOpenCharacter()
	{
		return openCharacter;
	}
	public static char getWallCharacter()
	{
		return wallCharacter;
	}
	public static char getStartCharacter()
	{
		return startCharacter;
	}
	public static char getEndCharacter()
	{
		return endCharacter;
	}
	public static Color getOpenColor()
	{
		return openColor;
	}
	public static Color getWallColor()
	{
		return wallColor;
	}
	public static Color getStartColor()
	{
		return startColor;
	}
	public static Color getEndColor()
	{
		return endColor;
	}
	
	

}
