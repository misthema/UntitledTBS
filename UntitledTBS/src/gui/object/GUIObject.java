package gui.object;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * Base abstract class for all objects, containing required methods and fields.
 * 
 * @author Jani
 * 
 */
public abstract class GUIObject
{
	Shape shape;
	boolean hover, clicked, enabled;

	/**
	 * Creates a new object. Field instantion must be done through childclass'
	 * init.
	 */
	public GUIObject()
	{}

	/**
	 * Creates a new object and initializes required fields.
	 * 
	 * @param shape
	 *            Shape for the object.
	 */
	public GUIObject(Shape shape)
	{
		this.init( shape );
	}

	/**
	 * Initialize the object. Must be called before any usage of the class.
	 * 
	 * @param shape
	 *            Shape for the object.
	 */
	protected void init ( Shape shape )
	{
		this.shape = shape;

		this.hover = false;
		this.clicked = false;
		this.enabled = true;
	}

	public abstract void update ( );

	public abstract void render ( );

	/**
	 * 
	 * @param x
	 *            Render offset X.
	 * @param y
	 *            Render offset Y.
	 */
	public abstract void render ( float x, float y );

	/**
	 * Event handler for click on object.
	 * 
	 * @param x
	 *            Mouse X
	 * @param y
	 *            Mouse Y
	 */
	public void click ( int x, int y )
	{}

	/* @formatter:off */

	public void      setHover   ( boolean v )     { this.hover = v; }
	public void      setClicked ( boolean click ) { this.clicked = click; }
	public void      enable     ( )               { this.enabled = true; }
	public void      disable    ( )               { this.enabled = false; }
	
	public GUIObject getObject  ( int id )        { return this; }
	public Shape     getShape   ( )               { return shape; }
	public boolean   isEnabled  ( )               { return enabled; }
	public boolean   isClicked  ( )               { return clicked; }
	public boolean   isHovered  ( )               { return hover; }
	
	/* @formatter:on */
}
