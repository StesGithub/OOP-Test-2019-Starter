package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class UI extends PApplet
{	
	public ArrayList<Color> colors = new ArrayList<Color>();
	ArrayList<Resistor> resistors = new ArrayList<Resistor>();

	public Color findColor(int num)
	{

		/*
		 * Searches the array list of colors for matching values with the colour value you passed
		 */
		for(Color c:colors)
		{
			if (c.num == num)
			{
				return c;
			}			
		}
		return null;
	}

	private void loadCodes()
	{
		/*
		 * This is the same as 2020 lab test
		 */
		Table t = loadTable("C:/GitHub/2019LabTest/OOP-Test-2019-Starter/data/colours.csv", "header");
		for(TableRow row:t.rows())
		{
			Color c = new Color(row);
			colors.add(c);
		}
	}

	private void loadResistors()
	{
		Table t = loadTable("C:/GitHub/2019LabTest/OOP-Test-2019-Starter/data/resistors.csv");
		for(TableRow row:t.rows())
		{			
			resistors.add(new Resistor(this,row.getInt(0)));
		}
	}

	private void printResistors()
	{
		/*
		 * This just prints all the values in our arraylist resistors
		 */
		for(Resistor r:resistors)
		{
			int i = r.value;
			int hundreds = (i / 100);
			int tens = (i - (hundreds * 100)) / 10;
			int ones = i - ((hundreds * 100)  + (tens * 10));
			print(hundreds + ",");
			print(tens + ",");
			println(ones);			
		}
	}

	public void settings()
	{
		size(500, 800);
		loadCodes();
		loadResistors();
		printResistors();
	}
	/*
	 * within processing draw and setup are called automatically in a loop
	 * setup 
	 * then draw
	 * 
	 */
	public void setup() 
	{
	}
	
	int resistorId = 0;
	boolean lastPressed = false;
	public void draw()
	{			
		background(200);
		stroke(255);

		for(int i = 0 ; i < resistors.size() ; i ++)
		{
			/*
			 * y value changes depending on what iteration of the loop were on
			 * Very clever bryan
			 */
			float y = map(i, 0, resistors.size(), 100, height - 100);
			resistors.get(i).render(width / 2, y); //this calls render 
			/*
			 * This calls render meaning its going to loop and draw the amount of resistors in the list
			 */
		}
		
	}
}
