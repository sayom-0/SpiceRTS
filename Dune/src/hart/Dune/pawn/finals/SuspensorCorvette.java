package hart.Dune.pawn.finals;

import hart.Dune.pawn.PawnManager;
import hart.Dune.pawn.types.ConstructPawn;
import javafx.scene.shape.Rectangle;

public final class SuspensorCorvette extends ConstructPawn<Rectangle>
{

	public SuspensorCorvette(PawnManager pm)
	{
		super("SuspensorCorvette", 60, pm, 125, 6000, 600);
	}

}
