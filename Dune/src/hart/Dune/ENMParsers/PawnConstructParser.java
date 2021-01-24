package hart.Dune.ENMParsers;

import hart.Valkyrie.objects.eventNodeManager.MethodParser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class PawnConstructParser extends MethodParser<Button, ActionEvent>
{

	@Override
	public void deConstruct()
	{

	}

	@Override
	public Button link(Button n, EventHandler<ActionEvent> e)
	{
		n.setOnAction(e);
		return n;
	}

}
