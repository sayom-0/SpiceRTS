package hart.Dune.pawn;

import javafx.scene.shape.Shape;

public abstract class Pawn<S extends Shape>
{
	protected S shape;
	private final String name;
	private final int Speed;
	private final int PAWNID;
	private final int maxHealth;
	private int health;
	private final boolean BUILDER;
	private boolean selected;

	protected Pawn(String name, int speed, S shape, PawnManager pm, int maxHealth)
	{
		this.name = name;
		Speed = speed;
		this.shape = shape;
		this.maxHealth = this.health = maxHealth;
		this.BUILDER = handleBuilder();
		this.PAWNID = handleID();
		this.getShape().setStrokeWidth(5);
		if (pm != null)
			pm.add(this);

		// Turn green when selected code
		this.getShape().setOnMouseClicked(e ->
		{
			pm.select(this);
		});

		//System.out.println(name + " : " + getPAWNID());
	}

	protected abstract boolean handleBuilder();

	protected abstract int handleID();

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
		return BUILDER;
	}

	public boolean isSelected()
	{
		return selected;
	}

	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}

	public int getHealth()
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
	}

	public int getMaxHealth()
	{
		return maxHealth;
	}

}
