package gui.object;

import org.newdawn.slick.Font;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class TextLabel extends GUIObject
{
	Font   font;
	String text;
	float  x, y;

	public TextLabel()
	{}

	public TextLabel(float x, float y, Font font, String text)
	{
		this.init( new Rectangle( x, y, font.getWidth( text ), font
		                   .getLineHeight( ) ), x, y, font, text );
	}

	protected void init ( Shape shape, float x, float y, Font font, String text )
	{
		super.init( shape );
		this.x = x;
		this.y = y;
		this.font = font;
		this.text = text;
	}

	@Override
	public void update ( boolean focus )
	{

	}

	@Override
	public void render ( )
	{
		render( 0, 0 );
	}

	@Override
	public void render ( float offx, float offy )
	{
		font.drawString( x + offx, y + offy, text );
	}

	public void setText ( String text )
	{
		this.text = text;
	}

	public String getText ( )
	{
		return this.text;
	}

}
