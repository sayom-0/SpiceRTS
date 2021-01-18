package hart.Dune.pawn;

import java.util.ArrayList;

import hart.Dune.pawn.finals.Ornithopter;
import hart.Dune.pawn.finals.SpiceCrawler;
import hart.Dune.pawn.types.ConstructPawn;

public class ConstructManager
{
	public static ArrayList<ConstructPawn> list;

	public static void loadIDs() // This MUST be run before anything pawn-related occurs or their ID's will not be registered
	{
		list = new ArrayList<>();
		list.add(new SpiceCrawler(null));
		list.add(new Ornithopter(null));
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
}