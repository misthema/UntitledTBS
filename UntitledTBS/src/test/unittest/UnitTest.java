package test.unittest;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class UnitTest extends BasicGame
{

	public UnitTest(String title)
	{
		super( title );
	}

	public void main ( String[] args )
	{
		try
		{
			AppGameContainer app = new AppGameContainer( new UnitTest(
			        "Unit test #1" ) );

			app.setDisplayMode( 800, 600, false );
			app.setTargetFrameRate( 60 );
			app.setShowFPS( true );
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
	public void render ( GameContainer arg0, Graphics arg1 )
	        throws SlickException
	{

	}

	@Override
	public void update ( GameContainer arg0, int arg1 ) throws SlickException
	{

	}

}
