package hart.Dune.pawn;

import javafx.scene.shape.Shape;

public abstract class Pawn<S extends Shape>
{
	protected S shape;
	private final String name;
	private final int Speed;
	private final int PAWNID;
	private final boolean builder;
	private boolean selected;

	protected Pawn(String name, int speed, S shape, int PAWNID, boolean builder, PawnManager pm)
	{
		this.name = name;
		Speed = speed;
		this.shape = shape;
		this.PAWNID = PAWNID;
		this.builder = builder;
		this.getShape().setStrokeWidth(5);
		pm.add(this);

		// Turn green when selected code
		this.getShape().setOnMouseClicked(e ->
		{
			pm.select(this);
		});
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

	public boolean isSelected()
	{
		return selected;
	}

	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}

}
