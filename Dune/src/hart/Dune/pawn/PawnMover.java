package hart.Dune.pawn;

import javafx.scene.shape.Shape;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;

public class PawnMover extends Thread
{
	private Shape shape;
	private Line line;
	private String name;
	private Thread t;
	private double tx, ty, ix, iy, speed;
	boolean xg = false, yg = false;

	public PawnMover(Pawn pawn, double x, double y, Line line)
	{
		this.shape = pawn.getShape();
		this.name = pawn.getName();
		this.speed = pawn.getSpeed();
		this.line = line;
		this.tx = x;
		this.ty = y;
	}

	public void run()
	{
		line.setStartX(shape.getTranslateX());
		line.setStartY(shape.getTranslateY());
		line.setEndX(tx);
		line.setEndY(ty);
		line.setTranslateX((shape.getTranslateX() + tx) / 2);
		line.setTranslateY((shape.getTranslateY() + ty) / 2);
		line.setFill(Color.GREEN);
		while (!xg || !yg)
		{
			line.setTranslateX((shape.getTranslateX() + tx) / 2);
			line.setTranslateY((shape.getTranslateY() + ty) / 2);
			line.setStartX(shape.getTranslateX());
			line.setStartY(shape.getTranslateY());
			if (tx > ix)
			{
				// +
				if (shape.getTranslateX() + speed < tx)
				{
					shape.setTranslateX(shape.getTranslateX() + speed);
				} else
				{
					xg = true;
				}
			} else
			{
				// -
				if (shape.getTranslateX() - speed > tx)
				{
					shape.setTranslateX(shape.getTranslateX() - speed);
				} else
				{
					xg = true;
				}
			}

			if (ty > iy)
			{
				// +
				if (shape.getTranslateY() + speed < ty)
				{
					shape.setTranslateY(shape.getTranslateY() + speed);
				} else
				{
					yg = true;
				}
			} else
			{
				// -
				if (shape.getTranslateY() - speed > ty)
				{
					shape.setTranslateY(shape.getTranslateY() - speed);
				} else
				{
					yg = true;
				}
			}
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		line.setVisible(false);
	}

	public void start()
	{
		if (t == null)
		{
			t = new Thread(this, "PawnMover : " + name);
			t.start();
		}
	}
}
