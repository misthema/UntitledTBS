package gui.object;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public class ImageObject extends GUIObject
{
	protected Image img;
	protected float x;
	protected float y;

	public ImageObject(Shape shape, Image img, float x, float y)
	{
		super( shape );
		this.img = img;
		this.x = x;
		this.y = y;
	}

	public void render ( )
	{
		img.draw( x, y );
	}

	@Override
	public void update ( )
	{

	}

	@Override
	public void render ( float offx, float offy )
	{
		img.draw( x + offx, y + offy );
	}

}
