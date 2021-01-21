package hart.Dune.pawn.types;

import hart.Dune.pawn.PawnManager;
import javafx.scene.shape.Shape;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public abstract class ConstructorPawn<S extends Shape> extends ConstructPawn<S>
{
	protected final int[] AUTHBUILD;
	private final VBox Menu;

	protected ConstructorPawn(String name, int speed, S shape, int PAWNID, PawnManager pm, int maxHealth,
			int[] AUTHBUILD)
	{
		super(name, speed, shape, PAWNID, pm, maxHealth, 10000, 10000);
		this.AUTHBUILD = AUTHBUILD;
		if (pm != null)// Required to avoid an exception during generation due too this being a
						// template and lacking an ID
			this.Menu = generateMenu();
		else
			this.Menu = null;
	}

	@Override
	protected boolean handleBuilder()
	{
		return true;
	}

	public VBox getMenu()
	{
		return Menu;
	}

	private VBox generateMenu()// TODO Make this work and not cause a mem leak
	{
		VBox menu = new VBox();
		HBox line = new HBox();
		ConstructPawn cc;
		for (int i = 0; i != AUTHBUILD.length; i++, line = new HBox())
		{
			cc = hart.Dune.pawn.ConstructManager.list.get(AUTHBUILD[i]);
			line.getChildren().add(hart.Valkyrie.objects.ScreenControllerFX.buildText(new Text(cc.getName()),
					Font.font("Roboto", FontWeight.NORMAL, FontPosture.REGULAR, 10)));

			line.getChildren()
					.add(hart.Valkyrie.objects.ScreenControllerFX.buildText(
							new Text("Build Cost : " + cc.getBUILDCOST() + " Build Time : " + cc.getBUILDTIME()),
							Font.font("Roboto", FontWeight.NORMAL, FontPosture.REGULAR, 10)));

			menu.getChildren().add(line);
		}

		return menu;
	}
}
