package hart.Dune.pawn;

import javafx.scene.shape.Shape;

public abstract class Pawn<S extends Shape>
{
	protected S shape;
	private final String name;
	private final int Speed;
	private final int PAWNID;
	private final boolean builder;

	protected Pawn(String name, int speed, S shape, int PAWNID, boolean builder)
	{
		this.name = name;
		Speed = speed;
		this.shape = shape;
		this.PAWNID = PAWNID;
		this.builder = builder;
	}

	public S getShape()
	{
		return shape;
	}

	public String getName()
	{
		return name;
	}

	public int getSpeed()
	{
		return Speed;
	}

	public int getPAWNID()
	{
		return PAWNID;
	}

	public boolean isBuilder()
	{
		return builder;
	}
}
