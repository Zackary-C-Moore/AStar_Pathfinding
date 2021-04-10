
public class Node 
{
	private int h;
	private int g;
	private int f;
	private NodeButton btn;
	private boolean openSpot = true;
	private char value = '-';
	
	//getters
	public int getHValue()
	{
		return h;
	}
	public int getGValue()
	{
		return g;
	}
	public int getFValue()
	{
		return f;
	}
	public NodeButton getNodeButton()
	{
		return btn;
	}
	public boolean isOpenSpot()
	{
		return openSpot;
	}
	public char getValue()
	{
		return value;
	}
	//setters
	public void setHValue(int h)
	{
		this.h = h;
	}
	public void setGValue(int g)
	{
		this.g = g;
	}
	public void setFValue(int f)
	{
		this.f = f;
	}
	public void setNodeButton(NodeButton btn)
	{
		this.btn = btn;
	}
	public void setOpenSpot(boolean o)
	{
		openSpot = o;
	}
	public void setValue(char v)
	{
		value = v;
	}
}
