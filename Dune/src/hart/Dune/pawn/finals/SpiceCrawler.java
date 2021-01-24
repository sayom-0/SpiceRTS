package hart.Dune.pawn.finals;

import javafx.scene.shape.Polygon;
import hart.Dune.pawn.PawnManager;
import hart.Dune.pawn.types.ConstructorPawn;

public final class SpiceCrawler extends ConstructorPawn<Polygon>
{

	public SpiceCrawler(PawnManager pm)
	{
		super("Spice Crawler", 35, new Polygon(), pm, 5000, new int[]
		{ 1, 2 });
		super.getShape().getPoints().addAll(new Double[]
		{ 200.0, 100.0, 100.0, 200.0, 300.0, 200.0 });
		super.getShape().setStyle("-fx-background-color: #000000");
	}

}
