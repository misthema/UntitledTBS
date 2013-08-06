package test.worldtest;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import worldgen.WorldManager;
import worldgen.tilemanager.TileManager;

import component.Camera;

public class WorldTest extends BasicGame
{

	public WorldTest(String title)
	{
		super( title );
	}

	WorldManager worldman;
	Camera       cam;

	public static void main ( String[] args )
	{
		try
		{
			BasicGame game = new WorldTest( "Perlin noise test #2" );

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
	public void init ( GameContainer gc ) throws SlickException
	{
		cam = new Camera( 0, 0 );

		worldman = new WorldManager( );
		worldman.setTileManager( new TileManager( ) );
		worldman.init( );
	}

	@Override
	public void update ( GameContainer gc, int delta ) throws SlickException
	{
		Input inp = gc.getInput( );

		int speed = (int) (5 / cam.getZoom( ));

		if (inp.isKeyDown( Input.KEY_W ))
		{
			cam.translate( new Vector2f( 0, -speed ) );
		}
		if (inp.isKeyDown( Input.KEY_S ))
		{
			cam.translate( new Vector2f( 0, speed ) );
		}
		if (inp.isKeyDown( Input.KEY_A ))
		{
			cam.translate( new Vector2f( -speed, 0 ) );
		}
		if (inp.isKeyDown( Input.KEY_D ))
		{
			cam.translate( new Vector2f( speed, 0 ) );
		}

		if (inp.isKeyDown( Input.KEY_Q ))
		{
			cam.setZoom( cam.getZoom( ) - 0.01f );
		}
		if (inp.isKeyDown( Input.KEY_E ))
		{
			cam.setZoom( cam.getZoom( ) + 0.01f );
		}
	}

	@Override
	public void render ( GameContainer gc, Graphics g ) throws SlickException
	{
		cam.startRender( g );

		int startX = (int) cam.getPosition( ).x;
		int startY = (int) cam.getPosition( ).y;
		int width = (int) (gc.getScreenWidth( ) / cam.getZoom( ));
		int height = (int) (gc.getScreenHeight( ) / cam.getZoom( ));

		worldman.getTileManager( ).render( startX, startY, width, height );

		cam.endRender( g );

		g.drawString( "Camera: " + startX + ", " + startY, 0, 0 );
	}

}