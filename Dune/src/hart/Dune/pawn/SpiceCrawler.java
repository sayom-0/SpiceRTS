package hart.Dune.pawn;

import javafx.scene.shape.Polygon;

public class SpiceCrawler extends Pawn<Polygon>
{

	public SpiceCrawler()
	{
		super("Spice Crawler", 35);
		super.setShape(new Polygon());
		super.getShape().getPoints().addAll(new Double[]
		{ 200.0, 100.0, 100.0, 200.0, 300.0, 200.0 });
		super.getShape().setStyle("-fx-background-color: #000000");
	}

}
