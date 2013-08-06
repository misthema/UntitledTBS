package test.guitest;

import gui.GUIHandler;

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
		public static final int EXIT = 0;
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

		gui.add( GUI.EXIT, MenuButton.create( "res/guitest/button1" ) );
	}

	@Override
	public void update ( GameContainer gc, StateBasedGame game, int delta )
	        throws SlickException
	{
		Input i = gc.getInput( );

		gui.update( i );
		if (gui.getObject( GUI.EXIT ).getClicked( )) gc.exit( );
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
