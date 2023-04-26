package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.*;


public class Gantt extends PApplet
{	
	
public ArrayList<Task> tasks = new ArrayList<Task>();

	public void settings()
	{
		size(800, 600);
	}

	public void loadTasks()
	{
		Table table = loadTable("tasks.csv", "header");

		for(TableRow r:table.rows())
		{
			Task t = new Task(r);
			tasks.add(t);
		}
	}

	public void printTasks()
	{
		for(Task t:tasks)
		{
			println(t);
		}
	}

	private boolean isEnd = false;
	private int whichTask = -1;
	private int whichDay = 30;

	private float border = width * 0.1f;
	private float rowheight = height * 0.1f;
	float namesPanel = width * 0.3f;
	
	public void mousePressed()
	{
		for(int i = 0; i < tasks.size(); i++)
		{
			float y = map(i, 0, tasks.size(), border, height - border);
			if(mouseX > namesPanel && mouseX < width && mouseY > y && mouseY < y + rowheight)
			{
				whichTask = i;
				whichDay = tasks.get(i).getStart();
				isEnd = false;
			}
		}
	}

	public void mouseDragged()
	{
		if (mouseX > width * 0.3f && mouseX < width * 0.9f && mouseY > height * 0.1f && mouseY < height * 0.9f)
		{
			println("Dragged mouse");
		}
	}

	void displayTasks()
	{
		float border = width * 0.1f;
		float left = width * 0.3f;
		float h = height * 0.1f;
		float rectStart = left + border;
		float rectEnd = width - border;
		float rectH = h * 0.5f;
		float textStart = left + border;
		for(int i = 0 ; i < tasks.size() ; i ++)
		{
			Task t = tasks.get(i);
			float y = map(i, 0, tasks.size(), border, height - border);
			noStroke();
			fill(255);
			textAlign(LEFT, CENTER);
			text(t.getTask(), textStart, y);
			float x1 = map(t.getStart(), 1, 30, rectStart, rectEnd);
			float x2 = map(t.getEnd(), 1, 30, rectStart, rectEnd);
			float w = x2 - x1;
			fill(255, 0, 0);
			rect(x1, y - rectH / 2, w, rectH);
		}
	}

	
	
	
	public void setup() 
	{
		colorMode(HSB);
		loadTasks();
		printTasks();

	}
	
	public void draw()
	{			
		background(0);
		displayTasks();
	}
}
