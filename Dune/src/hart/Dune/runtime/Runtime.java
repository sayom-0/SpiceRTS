package hart.Dune.runtime;

import java.util.HashMap;

import hart.Dune.pawn.Pawn;
import hart.Dune.pawn.PawnMover;
import hart.Dune.pawn.SpiceCrawler;
import hart.Valkyrie.objects.ScreenControllerFX;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

public class Runtime extends Application
{
	final static String VERSION = "Alpha 1";
	HashMap<Thread, Line> ThreadMap;
	ScreenControllerFX SCFX;
	BorderPane HUD;
	VBox TitleTextCenter;
	StackPane CenterUI;
	Pawn selectedPawn;

	@Override
	public void start(Stage stage) throws Exception
	{
		// Init Obj's
		SCFX = new ScreenControllerFX(1920, 1080);
		HUD = new BorderPane();
		TitleTextCenter = new VBox();
		CenterUI = new StackPane();
		ThreadMap = new HashMap<>();

		// Create Fonts
		SCFX.setFont("Title", Font.font("Open Sans", FontWeight.BOLD, FontPosture.REGULAR, 20));
		SCFX.setFont("SubTitle", Font.font("Open Sans", FontWeight.BOLD, FontPosture.REGULAR, 15));
		SCFX.setFont("Text", Font.font("Roboto", FontWeight.NORMAL, FontPosture.REGULAR, 10));

		// Set background color
		CenterUI.setStyle("-fx-background-color: #ffad40");
		HUD.setCenter(CenterUI);

		// Header Bar, Font, Text, Color, Centering etc...
		TitleTextCenter.getChildren().add(SCFX.setText("Title", new Text("Dune RTS " + VERSION), "Title"));
		SCFX.getText("Title").setFill(Color.WHITE);
		TitleTextCenter.setAlignment(Pos.CENTER);
		TitleTextCenter.setStyle("-fx-background-color: #000000");
		HUD.setTop(TitleTextCenter);

		gameStart();

		// Stage init stuff
		Scene scene = new Scene(HUD, SCFX.getRes("width"), SCFX.getRes("height"));
		stage.setScene(scene);
		stage.setTitle("Dune RTS " + VERSION);
		stage.show();

	}

	public static void main(String[] args)
	{
		launch(args);// Oh shit Oh god Oh fuck! It's main()! Everybody run for your lives!
	}

	public void gameStart()
	{
		SpiceCrawler spiceCrawler = new SpiceCrawler();
		CenterUI.getChildren().add(spiceCrawler.getShape());

		spiceCrawler.getShape().setOnMouseClicked(new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent me)
			{
				if (selectedPawn != null)
				{
					selectedPawn.getShape().setStrokeWidth(0);
					selectedPawn.getShape().setStroke(Color.TRANSPARENT);
				}
				selectedPawn = spiceCrawler;
				selectedPawn.getShape().setStrokeWidth(5);
				selectedPawn.getShape().setStroke(Color.GREEN);

				for (Thread t : ThreadMap.keySet())
				{
					if (t.getState() == Thread.State.TERMINATED)
					{
						CenterUI.getChildren().remove(ThreadMap.get(t));
					}
				}
			}

		});

		CenterUI.setOnMouseClicked(new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent me)
			{
				if (selectedPawn != null)
				{
					Line line = new Line();
					CenterUI.getChildren().add(line);
					Thread t = new Thread(new PawnMover(selectedPawn, me.getX() - (CenterUI.getWidth() * 0.5),
							me.getY() - (CenterUI.getHeight() * 0.5), line));
					t.start();
					ThreadMap.put(t, line);
				}
			}
		});
	}

}
