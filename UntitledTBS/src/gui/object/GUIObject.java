package gui.object;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public abstract class GUIObject
{
	Shape shape;
	boolean hover, clicked, enabled;

	public GUIObject(Shape shape)
	{
		this.shape = shape;

		this.hover = false;
		this.clicked = false;
		this.enabled = true;
	}

	public abstract void update ( );

	public abstract void render ( );

	public abstract void render ( float x, float y );

	public void click ( int x, int y )
	{}

	/* @formatter:off */

	public void      setHover   ( boolean v )     { this.hover = v; }
	public void      setClicked ( boolean click ) { this.clicked = click; }
	public void      enable     ( )               { this.enabled = true; }
	public void      disable    ( )               { this.enabled = false; }
	
	public GUIObject getObject  ( int id )        { return this; }
	public Shape     getShape   ( )               { return shape; }
	public boolean   getHover   ( )               { return hover; }
	public boolean   isEnabled  ( )               { return enabled; }
	public boolean   isClicked  ( )               { return clicked; }
	public boolean   isHovered  ( )               { return hover; }
	
	/* @formatter:on */
}
