package gui.object;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public class ImageObject extends GUIObject
{
	Image img, hoverimg;
	float x, y;

	public ImageObject(Shape shape, Image img, Image hoverimg, float x, float y)
	{
		super( shape );
		this.img = img;
		this.hoverimg = hoverimg;
		this.x = x;
		this.y = y;
	}

	public void render ( )
	{
		if (hover) hoverimg.draw( x, y );
		else img.draw( x, y );
	}

	@Override
	public void update ( )
	{

	}

	@Override
	public void render ( float offx, float offy )
	{
		if (hover) hoverimg.draw( x + offx, y + offy );
		else img.draw( x + offx, y + offy );
	}

}
