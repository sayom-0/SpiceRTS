package hart.Dune.pawn;

import javafx.scene.shape.Shape;

public abstract class Pawn<S extends Shape>
{
	private S shape;
	protected final PawnManager pm;
	private final String name;
	private final int Speed;
	private final int PAWNID;
	private final int maxHealth;
	private int health;
	private final boolean BUILDER;
	private boolean selected;

	protected Pawn(String name, int speed, PawnManager pm, int maxHealth)
	{
		this.name = name;
		Speed = speed;
		this.maxHealth = this.health = maxHealth;
		this.BUILDER = handleBuilder();
		this.PAWNID = handleID();
		this.shape = (S) hart.Dune.pawn.ConstructManager.getShape(this.PAWNID);
		this.pm = pm;
		if (shape != null)
		{
			// Shape stylez
			this.getShape().setStrokeWidth(5);
			this.getShape().setStyle("-fx-background-color: #000000");
		} else
		{
			System.out.println("		" + this
					+ " : Warning! Pawn Instantiated with a null shape, Pawn will not exist on game board!");
		}
		if (pm != null)
		{
			pm.add(this);
			// Turn green when selected code
			this.getShape().setOnMouseClicked(e ->
			{
				pm.select(this);
			});
		} else
		{
			System.out.println("		" + this
					+ " : Warning! Instantiated with a null PawnManager, Pawn will not be functional!");
		}

		// System.out.println(name + " : " + getPAWNID());

		// Make sure pawn is registered
		if (PAWNID == -1)
		{
			System.out.println("		" + this
					+ " : Warning! A pawn has been generated that is not indexed in the Construct Manager! This pawn will not be able to be Constructed and may cause a crash if it is ment to be!");
		}
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

	public PawnManager getPm()
	{
		return pm;
	}

}
