package hart.Dune.pawn;

import javafx.scene.shape.Shape;

public class PawnMover extends Thread
{
	private Shape shape;
	private String name;
	private Thread t;
	private double tx, ty, ix, iy, speed;
	boolean xg = false, yg = false;

	public PawnMover(Pawn pawn, double x, double y)
	{
		this.shape = pawn.getShape();
		this.name = pawn.getName();
		this.speed = pawn.getSpeed();
		this.tx = x;
		this.ty = y;
	}

	public void run()
	{
		while (!xg || !yg)
		{
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
