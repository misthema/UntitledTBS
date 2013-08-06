package gui;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;

import gui.GUIObject;

public class GUIHandler
{
	HashMap<Integer, GUIObject> objects;

	public GUIHandler()
	{
		this.objects = new HashMap<Integer, GUIObject>( );
	}

	public void add ( int id, GUIObject o )
	{
		this.objects.put( id, o );
	}

	public void disable ( int id )
	{
		this.objects.get( id ).setEnabled( false );
	}

	public void enable ( int id )
	{
		this.objects.get( id ).setEnabled( true );
	}

	public void update ( Input i )
	{
		boolean mousePressed = i.isMousePressed( Input.MOUSE_LEFT_BUTTON );
		for (GUIObject object : objects.values( ))
		{
			if (object.getEnabled( ))
			{
				Shape shape = object.getShape( );
				if (shape.contains( i.getMouseX( ), i.getMouseY( ) ))
				{
					object.setHover( true );

					if (mousePressed) object.setClicked( true );
					else object.setClicked( false );
				} else
				{
					object.setHover( false );
				}
			}
		}
	}

	public void render ( )
	{
		for (GUIObject object : objects.values( ))
			object.render( );
	}

	public GUIObject getObject ( int id )
	{
		return objects.get( id );
	}
}
