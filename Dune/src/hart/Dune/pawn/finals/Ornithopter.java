package hart.Dune.pawn.finals;

import hart.Dune.pawn.PawnManager;
import hart.Dune.pawn.types.ConstructPawn;
import javafx.scene.shape.Circle;

public final class Ornithopter extends ConstructPawn<Circle>
{

	public Ornithopter(PawnManager pm)
	{
		super("Ornithopter", 75, new Circle(), 1, pm, 50, 5000, 500);
	}

}
