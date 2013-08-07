package test.guitest;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import test.guitest.MenuState;

public class GUITest extends StateBasedGame
{

	public GUITest(String name)
	{
		super( name );
	}

	@Override
	public void initStatesList ( GameContainer gc ) throws SlickException
	{
		this.addState( new MenuState( ) );
	}

	public static void main ( String[] args )
	{
		try
		{
			StateBasedGame game = new GUITest( "GUI test" );

			AppGameContainer app = new AppGameContainer( game );
			app.setDisplayMode( 800, 600, false );
			app.setShowFPS( true );
			app.setTargetFrameRate( 60 );
			app.start( );
		} catch (SlickException e)
		{
			e.printStackTrace( );
			System.out.println( "Error occurred in Slick operation." );
		} catch (Exception e)
		{
			e.printStackTrace( );
		}
	}
}
