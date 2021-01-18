package hart.Dune.pawn;

import hart.Dune.construct.Buildable;
import javafx.scene.shape.Circle;

public class Ornithopter extends Pawn<Circle> implements Buildable
{
	private final int cost;
	private final int time;

	public Ornithopter(PawnManager pm)
	{
		super("Ornithopter", 75, new Circle(), 1, false, pm);
		this.cost = 500;
		this.time = 5;
	}

	@Override
	public int getCost()
	{
		return cost;
	}

	@Override
	public int getTime()
	{
		return time;
	}

}
