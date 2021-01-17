package hart.Dune.pawn;

import hart.Dune.construct.Builder;
import hart.Valkyrie.exceptions.IllegalDimensionsException;
import hart.Valkyrie.objects.ScreenControllerFX;
import hart.Valkyrie.util.BWindow;
import javafx.scene.Scene;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import java.util.ArrayList;
import java.util.List;

public class SpiceCrawler extends Pawn<Polygon> implements Builder
{
	class BuildMenu extends BWindow
	{
		private BorderPane pane;
		private final String name;
		private final List<Integer> Units;

		public BuildMenu(String name, List<Integer> Units)
		{
			try
			{
				SCFX = new ScreenControllerFX(800, 600);
			} catch (IllegalDimensionsException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pane = new BorderPane();
			this.name = name;
			this.Units = Units;
		}

		@Override
		public void start(Stage stage) throws Exception
		{
			Scene scene = new Scene(pane, SCFX.getRes("width"), SCFX.getRes("height"));
			stage.setScene(scene);
			stage.setTitle("Build Menu : " + name);
			stage.show();
		}

	}

	private BuildMenu menu;
	private ArrayList<Integer> Buildables;

	public SpiceCrawler()
	{
		super("Spice Crawler", 35, new Polygon(), 0, true);
		super.getShape().getPoints().addAll(new Double[]
		{ 200.0, 100.0, 100.0, 200.0, 300.0, 200.0 });
		super.getShape().setStyle("-fx-background-color: #000000");
		Buildables = new ArrayList<>();
		menu = new BuildMenu(super.getName(), Buildables);
	}

	@Override
	public void openMenu()
	{
		try
		{
			menu.window();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Integer> getUnitIDS()
	{
		return Buildables;
	}

}
