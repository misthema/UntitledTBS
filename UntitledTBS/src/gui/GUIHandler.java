package gui;

import java.util.HashMap;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;

import gui.object.GUIObject;

/**
 * 
 * @author Jani
 * 
 */
public class GUIHandler
{
	HashMap<Integer, GUIObject>  objects;
	HashMap<Integer, GUIHandler> handlers;
	GUIObject                    focus;
	float                        x, y;

	/**
	 * Creates a new GUIHandler.
	 */
	public GUIHandler()
	{
		this.objects = new HashMap<Integer, GUIObject>( );
		this.handlers = new HashMap<Integer, GUIHandler>( );
		this.x = 0;
		this.y = 0;
	}

	/**
	 * Creates a new GUIHandler with offset applied to it's objects.
	 * 
	 * @param x
	 * @param y
	 */
	public GUIHandler(float x, float y)
	{
		this( );
		this.x = x;
		this.y = y;
	}

	/**
	 * Adds an object to the handler.
	 * 
	 * @param id
	 *            ID for accessing the object.
	 * @param o
	 *            The object.
	 */
	public void add ( int id, GUIObject o )
	{
		this.objects.put( id, o );
	}

	/**
	 * Adds a child handler to the handler.
	 * 
	 * @param id
	 *            ID for accessing the handler.
	 * @param h
	 *            The handler.
	 */
	public void add ( int id, GUIHandler h )
	{
		this.handlers.put( id, h );
	}

	/**
	 * Removes an object from the handler.
	 * 
	 * @param id
	 *            ID of the object.
	 */
	public void removeObject ( int id )
	{
		this.objects.remove( id );
	}

	/**
	 * Removes a child handler from the handler.
	 * 
	 * @param id
	 *            ID of the handler.
	 * @return The handler for finishing operations.
	 */
	public GUIHandler removeHandler ( int id )
	{
		GUIHandler handler = this.handlers.get( id );
		this.handlers.remove( id );
		return handler;
	}

	/**
	 * Disables the handler and all objects.
	 */
	public void disable ( )
	{
		for (GUIObject object : objects.values( ))
			object.disable( );
	}

	/**
	 * Enables the handler and all objects.
	 */
	public void enable ( )
	{
		for (GUIObject object : objects.values( ))
			object.enable( );
	}

	/**
	 * Gets object with id and disables it.
	 * 
	 * @param id
	 */
	public void disable ( int id )
	{
		this.objects.get( id ).disable( );
	}

	/**
	 * Gets object with id and enables it.
	 * 
	 * @param id
	 */
	public void enable ( int id )
	{
		this.objects.get( id ).enable( );
	}

	/**
	 * Updates the handler.
	 * 
	 * @param i
	 */
	public void update ( Input i )
	{
		boolean mousePressed = i.isMousePressed( Input.MOUSE_LEFT_BUTTON );
		update( i, mousePressed );
	}

	protected void update ( Input i, boolean mousePressed )
	{
		int mouseX = i.getMouseX( );
		int mouseY = i.getMouseY( );
		for (GUIObject object : objects.values( ))
		{
			if (object.isEnabled( ))
			{
				object.setClicked( false );
				Shape shape = object.getShape( );
				shape.setLocation( shape.getX( ) + x, shape.getY( ) + y );
				if (shape.contains( mouseX, mouseY ))
				{
					object.setHover( true );

					if (mousePressed)
					{
						object.setClicked( true );
						object.click( mouseX, mouseY );
						focus = object;
					}
				} else
				{
					object.setHover( false );
				}
				shape.setLocation( shape.getX( ) - x, shape.getY( ) - y );
				object.update( );
			}
		}

		for (GUIHandler handler : handlers.values( ))
			handler.update( i, mousePressed, x, y );
	}

	/**
	 * Updates the handler, applying offset to it.
	 * 
	 * @param i
	 * @param offx
	 * @param offy
	 */
	public void update ( Input i, float offx, float offy )
	{
		this.x += offx;
		this.y += offy;
		update( i );
		this.x -= offx;
		this.y -= offy;
	}

	public void update ( Input i, boolean mousePressed, float offx, float offy )
	{
		this.x += offx;
		this.y += offy;
		update( i, mousePressed );
		this.x -= offx;
		this.y -= offy;
	}

	/**
	 * Renders the handler, child handlers and objects.
	 */
	public void render ( )
	{
		for (GUIObject object : objects.values( ))
			object.render( x, y );

		for (GUIHandler handler : handlers.values( ))
			handler.render( x, y );
	}

	/**
	 * Renders the handler, child handlers and objects with offset applied to
	 * them.
	 * 
	 * @param offx
	 * @param offy
	 */
	public void render ( float offx, float offy )
	{
		this.x += offx;
		this.y += offy;
		render( );
		this.x -= offx;
		this.y -= offy;
	}

	public GUIObject getObject ( int id )
	{
		return objects.get( id );
	}

	public GUIHandler getHandler ( int id )
	{
		return handlers.get( id );
	}

	public void setLocation ( float x, float y )
	{
		this.x = x;
		this.y = y;
	}
}
