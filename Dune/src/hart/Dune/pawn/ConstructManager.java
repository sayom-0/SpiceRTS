package hart.Dune.pawn;

import java.util.ArrayList;
import hart.Dune.pawn.finals.Ornithopter;
import hart.Dune.pawn.finals.SpiceCrawler;
import hart.Dune.pawn.finals.SuspensorCorvette;
import hart.Dune.pawn.types.ConstructPawn;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public final class ConstructManager
{
	public static ArrayList<ConstructPawn> list;

	public static void loadIDs() // This MUST be run before anything pawn-related occurs or their ID's will not
									// be registered
	{
		System.out.println("Loading PawnIDs into Construct Manager...");
		list = new ArrayList<>();

		System.out.println("	The following warnings can safely be ignored:");
		list.add(new SpiceCrawler(null));
		System.out.println();
		list.add(new Ornithopter(null));
		System.out.println();
		list.add(new SuspensorCorvette(null));
		System.out.println("	Warnings past this point should be taken seriously!");
	}

	public static int indexByName(String name)
	{
		for (int i = 0; i != list.size(); i++)
		{
			if (list.get(i).getName() == name)
				return i;
		}
		return -1;
	}

	public static Shape getShape(int ID)
	{
		switch (ID)
		{
		case 0:
			return new Polygon(200.0, 100.0, 100.0, 200.0, 300.0, 200.0);
		case 1:
			return new Circle(50);
		case 2:
			return new Rectangle(50, 25);
		}
		return null;
	}
}
