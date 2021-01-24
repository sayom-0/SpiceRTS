package hart.Dune.pawn;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;

public final class PawnManager
{
	private ArrayList<Pawn> Pawns;
	private final StackPane CenterUI;

	public PawnManager(StackPane CenterUI)
	{
		System.out.println("	Initalizing PawnManager...");
		Pawns = new ArrayList<>();
		this.CenterUI = CenterUI;
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
			getSelected().getShape().setStroke(Color.TRANSPARENT);
			getSelected().setSelected(false);
		}
		pawn.setSelected(true);
		pawn.getShape().setStroke(Color.GREEN);
	}

	public void add(Pawn pawn)
	{
		System.out.println("Adding pawn : " + pawn);
		Pawns.add(pawn);
	}

	public StackPane getCenterUI()
	{
		return CenterUI;
	}

}
