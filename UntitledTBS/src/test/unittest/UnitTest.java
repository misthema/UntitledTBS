package test.unittest;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import component.BaseUnit;

public class UnitTest extends BasicGame
{
	BaseUnit unit1, unit2;

	public UnitTest(String title)
	{
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException
	{
		this.unit1 = new BaseUnit("res/unittest/arrow.png");
		this.unit1.translate(200,
		                     200);

		this.unit2 = new BaseUnit("res/unittest/arrow.png");
		this.unit2.translate(200,
		                     220);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		unit1.draw();
		g.draw(unit1.getHit());

		unit2.draw();
		g.draw(unit2.getHit());

		g.drawString("Hits: " + (unit1.hits(unit2) ? "True" : "False"),
		             10,
		             30);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		Input i = gc.getInput();
		if (i.isKeyDown(Input.KEY_ESCAPE)) gc.exit();

		unit1.rotate(1);
		unit1.move(1);

		unit2.rotate(-1);
		unit2.move(1);
	}

	public static void main(String[] args)
	{
		try
		{
			BasicGame game = new UnitTest("Unit test");

			AppGameContainer app = new AppGameContainer(game);
			app.setDisplayMode(800,
			                   600,
			                   false);
			app.setShowFPS(true);
			app.setTargetFrameRate(60);
			app.start();
		}
		catch (SlickException e)
		{
			e.printStackTrace();
			System.out.println("Error occurred in Slick operation.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
