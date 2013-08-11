package test.guitest;

import java.awt.Font;

import gui.GUIHandler;
import gui.object.*;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
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
		Input i = gc.getInput( );
		Graphics g = gc.getGraphics( );
		this.game = game;
		this.gui = new GUIHandler( );

		GUIHandler menu = new GUIHandler( 200, 200 );
		menu.add( GUI.EXIT, new GUIButton( "res/guitest/button1", 0, 0 ) );
		gui.add( GUI.MENU, menu );

		TrueTypeFont font = new TrueTypeFont(
		                                      new Font(
		                                                Font.MONOSPACED,// "Arial",
		                                                Font.LAYOUT_LEFT_TO_RIGHT,
		                                                12 ), false );

		GUIHandler inputs = new GUIHandler( 150, 100 );
		inputs.add( 0, new InputField( g, i, 0, 0, 200, 20, font ) );
		inputs.add( 1, new InputField( g, i, 0, 30, 200, 20, font ) );
		gui.add( 1, inputs );

		GUIHandler input2 = new GUIHandler( 450, 100 );
		input2.add( 0, new InputField( g, i, 0, 0, 200, 20, font ) );
		input2.add( 1, new InputField( g, i, 0, 30, 200, 20, font ) );
		gui.add( 2, input2 );

		gui.add( 0, new TextLabel( 300, 60, font, "" ) );
	}

	@Override
	public void update ( GameContainer gc, StateBasedGame game, int delta )
	        throws SlickException
	{
		Input i = gc.getInput( );

		gui.update( i );
		if (gui.getHandler( GUI.MENU ).getObject( GUI.EXIT ).isClicked( ))
		    gc.exit( );

		if (i.isKeyDown( Input.KEY_ENTER ))
		{
			GUIObject o = gui.getFocusedObject( );
			if (o instanceof TextLabel)
			    ((TextLabel) (gui.getObject( 0 ))).setText( ((TextLabel) o)
			            .getText( ) );
		}
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
