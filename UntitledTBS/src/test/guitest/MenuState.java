package test.guitest;

import gui.GUIHandler;
import gui.object.menu.GUIButton;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState
{
	static class GUI
	{
		public static final int MENU  = 0;
		public static final int START = 0;
		public static final int EXIT  = 1;
	}

	StateBasedGame game;
	GUIHandler     gui;

	public MenuState()
	{}

	@Override
	public void init ( GameContainer gc, StateBasedGame game )
	        throws SlickException
	{
		this.game = game;
		this.gui = new GUIHandler( );

		GUIHandler menu = new GUIHandler( 200, 200 );
		menu.add( GUI.EXIT, new GUIButton( "res/guitest/button1", 0, 0 ) );
		gui.add( GUI.MENU, menu );
	}

	@Override
	public void update ( GameContainer gc, StateBasedGame game, int delta )
	        throws SlickException
	{
		Input i = gc.getInput( );

		gui.update( i );
		if (gui.getHandler( GUI.MENU ).getObject( GUI.EXIT ).isClicked( ))
		    gc.exit( );
	}

	@Override
	public void render ( GameContainer gc, StateBasedGame game, Graphics g )
	        throws SlickException
	{
		g.drawString( "In: Menu", 10, 30 );

		gui.render( );
	}

	@Override
	public int getID ( )
	{
		return 0;
	}

}
