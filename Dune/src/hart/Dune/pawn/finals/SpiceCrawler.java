package hart.Dune.pawn.finals;

import javafx.scene.shape.Polygon;
import hart.Dune.pawn.PawnManager;
import hart.Dune.pawn.types.ConstructorPawn;

public final class SpiceCrawler extends ConstructorPawn<Polygon>
{

	public SpiceCrawler(PawnManager pm)
	{
		super("Spice Crawler", 35, pm, 5000, new int[]
		{ 1, 2 });
	}

}
