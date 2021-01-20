package hart.Dune.pawn;

import javafx.scene.shape.Line;

import java.util.concurrent.atomic.AtomicBoolean;

import javafx.scene.paint.Color;

public final class PawnMover extends Thread
{
	private final Pawn pawn;
	private Line line;
	private Thread t;
	private double tx, ty, ix, iy;
	private AtomicBoolean running = new AtomicBoolean(false);
	boolean xg = false, yg = false;

	public PawnMover(Pawn pawn, double x, double y, Line line)
	{
		System.out.println("Initializing PawnMover Thread for pawn " + pawn);
		this.pawn = pawn;
		this.line = line;
		this.tx = x;
		this.ty = y;
		System.out.println("Done!");
	}

	public void run()
	{
		running.set(true);
		System.out.println("Moving pawn...");
		line.setStartX(pawn.getShape().getTranslateX());
		line.setStartY(pawn.getShape().getTranslateY());
		line.setEndX(tx);
		line.setEndY(ty);
		line.setTranslateX((pawn.getShape().getTranslateX() + tx) / 2);
		line.setTranslateY((pawn.getShape().getTranslateY() + ty) / 2);
		line.setFill(Color.GREEN);
		while ((!xg || !yg) && running.get())
		{
			line.setTranslateX((pawn.getShape().getTranslateX() + tx) / 2);
			line.setTranslateY((pawn.getShape().getTranslateY() + ty) / 2);
			line.setStartX(pawn.getShape().getTranslateX());
			line.setStartY(pawn.getShape().getTranslateY());
			if (tx > ix)
			{
				// +
				if (pawn.getShape().getTranslateX() + pawn.getSpeed() < tx)
				{
					pawn.getShape().setTranslateX(pawn.getShape().getTranslateX() + pawn.getSpeed());
				} else
				{
					xg = true;
				}
			} else
			{
				// -
				if (pawn.getShape().getTranslateX() - pawn.getSpeed() > tx)
				{
					pawn.getShape().setTranslateX(pawn.getShape().getTranslateX() - pawn.getSpeed());
				} else
				{
					xg = true;
				}
			}

			if (ty > iy)
			{
				// +
				if (pawn.getShape().getTranslateY() + pawn.getSpeed() < ty)
				{
					pawn.getShape().setTranslateY(pawn.getShape().getTranslateY() + pawn.getSpeed());
				} else
				{
					yg = true;
				}
			} else
			{
				// -
				if (pawn.getShape().getTranslateY() - pawn.getSpeed() > ty)
				{
					pawn.getShape().setTranslateY(pawn.getShape().getTranslateY() - pawn.getSpeed());
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
				System.out.println("			Ending PawnMover:" + pawn);
				running.set(false);
				line.setVisible(false);
				System.out.println("			Done!");
			}
		}
		System.out.println("Done!");
		running.set(false);
		line.setVisible(false);
	}

	public void start()
	{
		if (t == null)
		{
			t = new Thread(this, "PawnMover:" + pawn);
			this.setName("PawnMover:" + pawn);
			t.start();
		}
	}

	public Pawn getPawn()
	{
		return pawn;
	}

}
