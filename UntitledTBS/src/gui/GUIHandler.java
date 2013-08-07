package gui;

import java.util.HashMap;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;

import gui.object.GUIObject;

public class GUIHandler
{
	HashMap<Integer, GUIObject>  objects;
	HashMap<Integer, GUIHandler> handlers;
	GUIObject                    focus;
	float                        x, y;

	public GUIHandler()
	{
		this.objects = new HashMap<Integer, GUIObject>( );
		this.handlers = new HashMap<Integer, GUIHandler>( );
		this.x = 0;
		this.y = 0;
	}

	public GUIHandler(float x, float y)
	{
		this( );
		this.x = x;
		this.y = y;
	}

	public void add ( int id, GUIObject o )
	{
		this.objects.put( id, o );
	}

	public void add ( int id, GUIHandler h )
	{
		this.handlers.put( id, h );
	}

	public void disable ( )
	{
		for (GUIObject object : objects.values( ))
			object.disable( );
	}

	public void enable ( )
	{
		for (GUIObject object : objects.values( ))
			object.enable( );
	}

	public void disable ( int id )
	{
		this.objects.get( id ).disable( );
	}

	public void enable ( int id )
	{
		this.objects.get( id ).enable( );
	}

	public void update ( Input i )
	{
		boolean mousePressed = i.isMousePressed( Input.MOUSE_LEFT_BUTTON );
		update( i, mousePressed );
	}

	public void update ( Input i, boolean mousePressed )
	{
		int mouseX = i.getMouseX( );
		int mouseY = i.getMouseY( );
		for (GUIObject object : objects.values( ))
		{
			if (object.isEnabled( ))
			{
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
					} else object.setClicked( false );
				} else
				{
					object.setHover( false );
				}
				shape.setLocation( shape.getX( ) - x, shape.getY( ) - y );
			}
		}

		for (GUIHandler handler : handlers.values( ))
			handler.update( i, mousePressed, x, y );
	}

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

	public void render ( )
	{
		for (GUIObject object : objects.values( ))
			object.render( x, y );

		for (GUIHandler handler : handlers.values( ))
			handler.render( x, y );
	}

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
