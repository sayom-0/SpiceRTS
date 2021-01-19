package hart.Dune.pawn;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public final class PawnManager
{
	private ArrayList<Pawn> Pawns;

	public PawnManager()
	{
		System.out.println("	Initalizing PawnManager...");
		Pawns = new ArrayList<>();
		System.out.println("	Done!");
	}

	public Pawn getSelected()
	{
		for (int i = 0; i != Pawns.size(); i++)
		{
			if (Pawns.get(i).isSelected())
				return Pawns.get(i);
		}
		return null;
	}

	public void select(Pawn pawn)
	{
		System.out.println("Pawn : " + pawn + " Selected!");
		if (getSelected() != null)
		{
			getSelected().setSelected(false);
			getSelected().getShape().setStroke(Color.TRANSPARENT);
		}
		pawn.setSelected(true);
		pawn.getShape().setStroke(Color.GREEN);
	}

	public void add(Pawn pawn)
	{
		System.out.println("Adding pawn : " + pawn);
		Pawns.add(pawn);
	}

}
