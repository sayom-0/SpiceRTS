package hart.Dune.pawn.types;

import hart.Dune.ENMParsers.PawnConstructParser;
import hart.Dune.pawn.PawnManager;
import javafx.scene.shape.Shape;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ConstructorPawn<S extends Shape> extends ConstructPawn<S>
{
	protected final int[] AUTHBUILD;
	private final VBox Menu;

	protected ConstructorPawn(String name, int speed, S shape, PawnManager pm, int maxHealth, int[] AUTHBUILD)
	{
		super(name, speed, shape, pm, maxHealth, 10000, 10000);
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

	private VBox generateMenu()// TODO work on format, functionality
	{
		System.out.println(this + " : Generating build menu");
		VBox menu = new VBox(5);
		HBox line = new HBox(10);
		line.setPadding(new Insets(15, 15, 15, 15));
		menu.setPadding(new Insets(15, 15, 15, 15));
		ConstructPawn cc;
		for (int i = 0; i != AUTHBUILD.length; i++, line = new HBox())
		{
			System.out.println("	Adding line for pawnID: " + AUTHBUILD[i]);
			cc = hart.Dune.pawn.ConstructManager.list.get(AUTHBUILD[i]);
			line.getChildren().add(hart.Valkyrie.objects.ScreenControllerFX.buildText(new Text(cc.getName()),
					Font.font("Roboto", FontWeight.NORMAL, FontPosture.REGULAR, 15)));

			line.getChildren()
					.add(hart.Valkyrie.objects.ScreenControllerFX.buildText(new Text(
							"[Build Cost : " + cc.getBUILDCOST() + "     Build Time : " + cc.getBUILDTIME() + "]"),
							Font.font("Roboto", FontWeight.NORMAL, FontPosture.REGULAR, 15)));
			ConstructPawn pawn = cc.copy();
			line.getChildren()
					.add(hart.Valkyrie.util.Utils.buildEventNode(new Button("Build"), new EventHandler<ActionEvent>()
					{

						@Override
						public void handle(ActionEvent e)
						{
							// Do magic shit to add a clone of the pawn to the CenterUI
							pm.getCenterUI().getChildren().add(pawn.copy().getShape());
						}
					}, new PawnConstructParser()));

			menu.getChildren().add(line);
		}

		return menu;
	}
}
