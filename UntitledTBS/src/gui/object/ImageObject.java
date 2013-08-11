package gui.object;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * A GUI class designed to draw an image to the screen. For deriving purposes.
 * 
 * @author Jani
 * 
 */
public class ImageObject extends GUIObject
{
	protected Image img;
	protected float x;
	protected float y;

	public ImageObject()
	{}

	public ImageObject(Shape shape, Image img, float x, float y)
	{
		this.init( shape, img, x, y );
	}

	protected void init ( Shape shape, Image img, float x, float y )
	{
		super.init( shape );
		this.img = img;
		this.x = x;
		this.y = y;
	}

	public void render ( )
	{
		render( 0, 0 );
	}

	@Override
	public void update ( boolean focus )
	{

	}

	@Override
	public void render ( float offx, float offy )
	{
		img.draw( x + offx, y + offy );
	}

}
