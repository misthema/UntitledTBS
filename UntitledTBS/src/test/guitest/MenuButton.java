package test.guitest;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import gui.GUIObject;

public class MenuButton extends GUIObject
{

	public MenuButton(Shape shape, Image img, Image hoverimg, float x, float y)
	{
		super( shape, img, hoverimg, x, y );
	}

	public static MenuButton create ( String name ) throws SlickException
	{
		Image img = new Image( name + ".png" );
		Image hoverimg = new Image( name + "hover.png" );
		Rectangle rect = new Rectangle( 200, 200, img.getWidth( ),
		                                img.getHeight( ) );
		return new MenuButton( rect, img, hoverimg, 200, 200 );
	}
}
