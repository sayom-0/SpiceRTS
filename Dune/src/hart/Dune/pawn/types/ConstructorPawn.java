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

	protected ConstructorPawn(String name, int speed, S shape, int PAWNID, PawnManager pm, int maxHealth,
			int[] AUTHBUILD)
	{
		super(name, speed, shape, PAWNID, pm, maxHealth, 10000, 10000);
		this.AUTHBUILD = AUTHBUILD;
	}

	@Override
	protected boolean handleBuilder()
	{
		return true;
	}

	public VBox getMenu()//TODO Make this work and not cause a mem leak
	{
		VBox menu = new VBox();
		HBox line = new HBox();
		Text text = new Text();
		text.setFont(Font.font("Roboto", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		ConstructPawn cc;
		for (int i = 0; i != AUTHBUILD.length; i++, line = new HBox())
		{
			cc = hart.Dune.pawn.ConstructManager.list.get(AUTHBUILD[i]);
			text.setText(cc.getName());
			line.getChildren().add(text);

			text.setText("Build Cost : " + cc.getBUILDCOST() + " Build Time : " + cc.getBUILDTIME());
			line.getChildren().add(text);

			menu.getChildren().add(line);
		}

		return menu;
	}
}
