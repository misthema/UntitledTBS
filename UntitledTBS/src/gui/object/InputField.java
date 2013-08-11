package gui.object;

import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class InputField extends TextLabel implements KeyListener
{
	float          x, y, width, height;
	Input          input;
	boolean        focus, showCursorLine, listenerOn;
	int            cursorPos, cursorScreenPos, lineHeight, sideScrollPos,
	        charWidth;
	Graphics       graphics;
	Timer          timer;

	TimerTask      flashTimer, keyTimer;
	int            timerKey;

	private String visibleText;

	public InputField(Graphics g, Input i, float x, float y, float width,
	                  float height, Font font)
	{
		this.init( g, i, new Rectangle( x, y, width, height ), x, y, width,
		           height, font );
	}

	protected void init ( Graphics g, Input i, Shape shape, float x, float y,
	                      float width, float height, Font font )
	{
		super.init( shape, x, y, font, "Normal text" );
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.focus = false;

		this.input = i;
		input.addKeyListener( this );
		this.listenerOn = true;

		this.graphics = g;

		this.cursorPos = text.length( );
		this.lineHeight = font.getLineHeight( );
		this.showCursorLine = false;
		this.sideScrollPos = 0;
		this.charWidth = (int) (width / font.getWidth( "_" ));
		this.visibleText = "";
		updateVisibleText( );
		this.cursorScreenPos = this.charWidth;

		timer = new Timer( );
	}

	protected void updateVisibleText ( )
	{
		/*
		 * String dtext = ""; int w = 0; for (int i = sideScrollPos; i <=
		 * text.length( ); i++) { if (text.subSequence( i, i ) == " ") continue;
		 * String s = text.substring( sideScrollPos, i ); if (font.getWidth( s )
		 * > width) break; dtext = s; w++; } // charWidth = w - 1;
		 */
		if (text.length( ) > charWidth)
		{
			visibleText = text.substring( sideScrollPos, sideScrollPos
			        + charWidth );
		} else visibleText = text;
	}

	@Override
	public void update ( boolean focus )
	{
		if (focus && !this.focus) startFlashTimer( );
		else if (!focus && this.focus) stopFlashTimer( );
		this.focus = focus;
	}

	@Override
	public void render ( )
	{
		render( 0, 0 );
	}

	@Override
	public void render ( float offx, float offy )
	{
		Color oldColor = graphics.getColor( );
		float x = this.x + offx;
		float y = this.y + offy;
		graphics.setColor( Color.black );
		graphics.fillRect( x, y, width, height );
		graphics.setColor( Color.white );
		font.drawString( x + 2, y + 2, visibleText );
		if (showCursorLine)
		{
			float lineX = x
			        + font.getWidth( text.substring( sideScrollPos, cursorPos ) )
			        + 1;
			graphics.drawLine( lineX, y, lineX, y + lineHeight );
		}
		graphics.drawRect( x, y, width, height );
		graphics.setColor( oldColor );
	}

	@Override
	public void inputEnded ( )
	{

	}

	@Override
	public void inputStarted ( )
	{

	}

	@Override
	public boolean isAcceptingInput ( )
	{
		return focus && enabled;
	}

	@Override
	public void setInput ( Input arg0 )
	{

	}

	@Override
	public void keyPressed ( int key, char c )
	{
		if (handleKey( key, c ))
		{
			timerKey = key;
			if (keyTimer != null)
			{
				keyTimer.cancel( );
				timer.purge( );
			}
			keyTimer = new KeyTimerTask( key, c ) {
				@Override
				public void run ( )
				{
					handleKey( key, c );
				}
			};
			timer.scheduleAtFixedRate( keyTimer, 500, 50 );
		}
	}

	@Override
	public void keyReleased ( int key, char c )
	{
		if (key == timerKey)
		{
			if (keyTimer != null)
			{
				keyTimer.cancel( );
				timer.purge( );
				keyTimer = null;
			}
		}
	}

	public boolean handleKey ( int key, char c )
	{
		switch (key)
		{
			case Input.KEY_BACK:
				if (cursorPos > 0 & text.length( ) > 0)
				{
					text = text.substring( 0, cursorPos - 1 )
					        + text.substring( cursorPos );
					// updateVisibleText( );
					cursorLeft( );
					// scrollLeft( );
				}
				break;
			case Input.KEY_LEFT:
				cursorLeft( );
				break;
			case Input.KEY_RIGHT:
				cursorRight( );
				break;
			default:
				if (isPrintable( c ))
				{
					text = text.substring( 0, cursorPos ) + c
					        + text.substring( cursorPos );
					// updateVisibleText( );
					cursorRight( );
					// scrollRight( );
				}
				break;
		}
		updateVisibleText( );
		return true;
	}

	public void cursorRight ( )
	{
		if (cursorPos < text.length( )) cursorPos++;
		if (cursorScreenPos < charWidth) cursorScreenPos++;
		else scrollRight( );
		if (cursorScreenPos > charWidth) cursorScreenPos = charWidth;
	}

	public void cursorLeft ( )
	{
		if (cursorPos > 0) cursorPos--;
		if (cursorScreenPos > 1) cursorScreenPos--;
		else scrollLeft( );
	}

	public void scrollLeft ( )
	{
		if (sideScrollPos > 0) sideScrollPos--;
	}

	public void scrollRight ( )
	{
		if (sideScrollPos < text.length( ) - charWidth) sideScrollPos++;
	}

	public void startFlashTimer ( )
	{
		if (flashTimer == null)
		{
			flashTimer = new TimerTask( ) {
				@Override
				public void run ( )
				{
					showCursorLine = !showCursorLine;
				}
			};
			timer.scheduleAtFixedRate( flashTimer, 0, 500 );
		}
	}

	public void stopFlashTimer ( )
	{
		flashTimer.cancel( );
		timer.purge( );
		flashTimer = null;
		showCursorLine = false;
	}

	@Override
	public void enable ( )
	{
		if (!listenerOn)
		{
			input.addKeyListener( this );
			listenerOn = true;
		}
	}

	@Override
	public void disable ( )
	{
		if (listenerOn)
		{
			input.removeKeyListener( this );
			listenerOn = false;
		}
	}

	protected boolean isPrintable ( char c )
	{
		Character.UnicodeBlock block = Character.UnicodeBlock.of( c );
		return (!Character.isISOControl( c )) && block != null
		        && block != Character.UnicodeBlock.SPECIALS;
	}
}

abstract class KeyTimerTask extends TimerTask
{
	int  key;
	char c;

	KeyTimerTask(int key, char c)
	{
		this.key = key;
		this.c = c;
	}
}