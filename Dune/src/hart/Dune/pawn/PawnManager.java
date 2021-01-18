package hart.Dune.pawn;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public class PawnManager
{
	private ArrayList<Pawn> Pawns;

	public PawnManager()
	{
		Pawns = new ArrayList<>();
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
		Pawns.add(pawn);
	}

}
