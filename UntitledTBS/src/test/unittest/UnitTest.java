package test.unittest;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import test1.Test1;
import component.BaseUnit;

public class UnitTest extends BasicGame
{
	BaseUnit unit;

	public UnitTest(String title)
	{
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException
	{
		this.unit = new BaseUnit("res/unittest/arrow.png");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		this.unit.draw();
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{

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
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
