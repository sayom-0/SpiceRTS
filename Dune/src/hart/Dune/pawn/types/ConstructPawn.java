package hart.Dune.pawn.types;

import hart.Dune.pawn.Pawn;
import hart.Dune.pawn.PawnManager;
import javafx.scene.shape.Shape;

public abstract class ConstructPawn<S extends Shape> extends Pawn<S>
{
	protected final int BUILDTIME;// in milliseconds
	protected final int BUILDCOST;

	protected ConstructPawn(String name, int speed, S shape, int PAWNID, PawnManager pm, int maxHealth, int BUILDTIME,
			int BUILDCOST)
	{
		super(name, speed, shape, pm, maxHealth);
		this.BUILDTIME = BUILDTIME;
		this.BUILDCOST = BUILDCOST;
	}

	@Override
	protected boolean handleBuilder()
	{
		return false;
	}

	@Override
	protected int handleID()
	{
		return hart.Dune.pawn.ConstructManager.indexByName(getName());
	}

	public int getBUILDTIME()
	{
		return BUILDTIME;
	}

	public int getBUILDCOST()
	{
		return BUILDCOST;
	}

}
