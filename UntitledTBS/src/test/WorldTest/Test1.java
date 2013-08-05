package test.WorldTest;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Test1 extends BasicGame
{

	public Test1(String title)
	{
		super( title );
	}

	public static void main ( String[] args )
	{
		try
		{
			BasicGame game = new Test1( "Perlin noise test #2" );

			AppGameContainer app = new AppGameContainer( game );
			app.setDisplayMode( 800, 600, false );
			app.setShowFPS( true );
			app.setTargetFrameRate( 60 );
			app.start( );
		} catch (Exception e)
		{
			e.printStackTrace( );
		}

	}

	@Override
	public void init ( GameContainer arg0 ) throws SlickException
	{

	}

	@Override
	public void update ( GameContainer arg0, int arg1 ) throws SlickException
	{

	}

	@Override
	public void render ( GameContainer arg0, Graphics arg1 )
	        throws SlickException
	{

	}

}