package gui;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public abstract class GUIObject
{
	Shape shape;
	Image img, hoverimg;
	float x, y;
	boolean hover, clicked, enabled;

	public GUIObject(Shape shape, Image img, Image hoverimg, float x, float y)
	{
		this.shape = shape;
		this.img = img;
		this.hoverimg = hoverimg;
		this.x = x;
		this.y = y;
		this.hover = false;
		this.clicked = false;
		this.enabled = true;
	}

	public void render ( )
	{
		if (hover) hoverimg.draw( x, y );
		else img.draw( x, y );
	}

	/* @formatter:off */

	public void      setHover   ( boolean v )     { this.hover = v; }
	public void      setClicked ( boolean click ) { this.clicked = click; }
	public void      setEnabled ( boolean t)      { this.enabled = t; }

	public Shape     getShape   ( )               { return shape; }
	public boolean   getClicked ( )               { return clicked; }
	public boolean   getHover   ( )               { return hover; }
	public boolean   getEnabled ( )               { return enabled; }
	
	/* @formatter:on */
}
