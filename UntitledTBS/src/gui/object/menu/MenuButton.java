package gui.object.menu;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import gui.object.ImageObject;

public class MenuButton extends ImageObject
{

	public MenuButton(Shape shape, Image img, Image hoverimg, float x, float y)
	{
		super( shape, img, hoverimg, x, y );
	}

	public static MenuButton create ( String name, float x, float y )
	        throws SlickException
	{
		Image img = new Image( name + ".png" );
		Image hoverimg = new Image( name + "hover.png" );
		Rectangle rect = new Rectangle( x, y, img.getWidth( ), img.getHeight( ) );
		return new MenuButton( rect, img, hoverimg, x, y );
	}
}
