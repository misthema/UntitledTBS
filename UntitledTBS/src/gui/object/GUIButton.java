package gui.object;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * GUI element, which has graphical impact when clicked.
 * 
 * @author Jani
 * 
 */
public class GUIButton extends ImageObject
{
	Image hoverimg;

	public GUIButton()
	{}

	public GUIButton(String name, float x, float y) throws SlickException
	{
		Image img = new Image( name + ".png" );
		Image hoverimg = new Image( name + "hover.png" );
		Rectangle rect = new Rectangle( x, y, img.getWidth( ), img.getHeight( ) );
		this.init( rect, img, hoverimg, x, y );
	}

	public GUIButton(Shape shape, Image img, Image hoverimg, float x, float y)
	{
		this.init( shape, img, hoverimg, x, y );
	}

	protected void init ( Shape shape, Image img, Image hoverimg, float x,
	                      float y )
	{
		super.init( shape, img, x, y );
		this.hoverimg = hoverimg;
	}

	@Override
	public void render ( )
	{
		if (isHovered( )) hoverimg.draw( x, y );
		else img.draw( x, y );
	}

	@Override
	public void render ( float offx, float offy )
	{
		float x = this.x + offx;
		float y = this.y + offy;
		if (isHovered( )) hoverimg.draw( x, y );
		else img.draw( x, y );
	}
}
