package hart.Dune.pawn.types;

import hart.Dune.pawn.Pawn;
import hart.Dune.pawn.PawnManager;
import javafx.scene.shape.Shape;

public class ConstructPawn<S extends Shape> extends Pawn<S>
{
	protected final int BUILDTIME;// in milliseconds
	protected final int BUILDCOST;

	public ConstructPawn(ConstructPawn<S> pawn, PawnManager pm)
	{
		super(pawn.getName(), pawn.getSpeed(), pm, pawn.getMaxHealth());
		this.BUILDTIME = pawn.getBUILDTIME();
		this.BUILDCOST = pawn.getBUILDCOST();
	}

	protected ConstructPawn(String name, int speed, PawnManager pm, int maxHealth, int BUILDTIME,
			int BUILDCOST)
	{
		super(name, speed, pm, maxHealth);
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
