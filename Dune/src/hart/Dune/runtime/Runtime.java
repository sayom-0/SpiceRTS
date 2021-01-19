package hart.Dune.runtime;

import java.util.concurrent.ConcurrentHashMap;
import hart.Dune.pawn.PawnManager;
import hart.Dune.pawn.PawnMover;
import hart.Dune.pawn.finals.SpiceCrawler;
import hart.Dune.pawn.types.ConstructorPawn;
import hart.Valkyrie.objects.ScreenControllerFX;
import javafx.application.Application;
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
import javafx.scene.shape.Line;
import javafx.scene.input.KeyCode;

public class Runtime extends Application
{
	final static String VERSION = "Alpha 3.2";
	ConcurrentHashMap<Thread, Line> ThreadMap;
	ScreenControllerFX SCFX;
	BorderPane HUD;
	VBox TitleTextCenter;
	StackPane CenterUI;
	PawnManager pm;
	Scene scene;
	boolean menuOpen;

	@Override
	public void start(Stage stage) throws Exception
	{
		// Init ConstructManager
		hart.Dune.pawn.ConstructManager.loadIDs();

		// Init Obj's
		System.out.println("Constructing Runtime Objects...");
		SCFX = new ScreenControllerFX(1920, 1080);
		HUD = new BorderPane();
		TitleTextCenter = new VBox();
		CenterUI = new StackPane();
		ThreadMap = new ConcurrentHashMap<>();
		pm = new PawnManager();
		menuOpen = false;
		System.out.println("Done!");

		// Create Fonts
		System.out.println("Generating UI...");
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
		System.out.println("Done!");

		// Stage init stuff
		scene = new Scene(HUD, SCFX.getRes("width"), SCFX.getRes("height"));
		gameStart();
		stage.setScene(scene);
		stage.setTitle("Dune RTS " + VERSION);
		stage.show();

	}

	public static void main(String[] args)
	{
		launch(args);// Oh shit Oh god Oh fuck! It's main()! Everybody run for your lives!
	}

	@SuppressWarnings(
	{ "rawtypes" })
	public void gameStart()
	{
		System.out.println("Populating game board...");
		SpiceCrawler spiceCrawler = new SpiceCrawler(pm);
		CenterUI.getChildren().add(spiceCrawler.getShape());

		CenterUI.setOnMouseClicked(e -> // movement code
		{
			System.out.println("Walking ThreadMap for removable threads...");
			for (Thread t : ThreadMap.keySet()) // Remove terminated threads and their movements lines
			{
				System.out.println("	Reviewing thread : " + t.getName());
				if ((t.getState() == Thread.State.TERMINATED) || t.isInterrupted())
				{
					System.out.println("		Removing terminated PawnMover...");
					CenterUI.getChildren().remove(ThreadMap.remove(t));
					System.out.println("		Done!");
				} else if (t.getName().equals("PawnMover:" + pm.getSelected()))// TODO find some way to tell if the
																				// thread we are looking at is
																				// moving the selected pawn
				{// No, no you can't just cast a Thread to a subclass to make this easy
					// And no, you can't just convert the ThreadMap to <PawnMover,Line> because
					// somehow that breaks Thread.State recognition, trust me, i've tried...
					// Yes I tried PawnMover.State... No
					// What I've tried so far: Comparing thread names, Comparing Pawn names,
					// Comparing Pawns.
					// (Used both .equals (do i need to override java.lang.objects equals()? yes, do
					// that(No don't you can't modify Thread's implementation and can't use a PawnMover)) and ==)
					System.out.println("		Terminating canceled PawnMover...");
					t.interrupt();
					// CenterUI.getChildren().remove(ThreadMap.remove(t));
					System.out.println("		Done!");
				}
			} // This for loop is my standing testament of FUCK YOU to whoever implemented
				// multi-threading on the JVM
			System.out.println("Done!");

			if (pm.getSelected() != null)// This must be run after the ThreadMap has been cleaned to avoid a concurrent
											// modification exception due to PawnMover threads modifying lines in the
											// ThreadMap
			{
				Line line = new Line();
				CenterUI.getChildren().add(line);
				Thread t = new Thread(new PawnMover(pm.getSelected(), e.getX() - (CenterUI.getWidth() * 0.5),
						e.getY() - (CenterUI.getHeight() * 0.5), line));
				t.setName("PawnMover:" + pm.getSelected());
				t.start();
				ThreadMap.put(t, line);
			}
		});

		scene.setOnKeyPressed(e ->
		{
			if ((pm.getSelected() != null) && (e.getCode() == KeyCode.B) && pm.getSelected().isBuilder() && !menuOpen)
			{// Build menu block start
				HUD.setRight(((ConstructorPawn) pm.getSelected()).getMenu());
				menuOpen = true;
				System.out.println("Opened!");
			} else if (menuOpen && (e.getCode() == KeyCode.B))
			{
				HUD.setRight(null);
				menuOpen = false;
				System.out.println("Closed!");
			} // end of build menu block
		});
		System.out.println("Done!");
	}

}
