package hart.Dune.pawn;

import javafx.scene.shape.Shape;

public class Pawn<S extends Shape>
{
	private S shape;
	private String name;
	private int Speed;

	public Pawn(String name, int speed)
	{
		this.name = name;
		Speed = speed;
	}

	public S getShape()
	{
		return shape;
	}

	public void setShape(S shape)
	{
		this.shape = shape;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getSpeed()
	{
		return Speed;
	}

	public void setSpeed(int speed)
	{
		Speed = speed;
	}
}
