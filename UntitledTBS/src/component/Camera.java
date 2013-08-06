package component;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Camera
{

	Vector2f      position;

	private float zoom;

	float         scale;

	BaseUnit      follow;

	public Camera(float x, float y)
	{
		this.position = new Vector2f( x, y );
		this.setZoom( 1.0f );
		this.scale = 1.0f;
	}

	/* @formatter:off */
	public Vector2f getPosition() { return this.position; }
	public float getZoom ( ) { return zoom; }
	public void setPosition ( Vector2f newPos )	{ this.position = newPos; }
	public void setZoom ( float zoom ) { this.zoom = zoom; }
	/* @formatter:on */

	public void translate ( Vector2f addPos )
	{
		this.position.add( addPos );
	}

	public void startRender ( Graphics g )
	{
		// TODO PushMatrix and -Translate
		g.pushTransform( );
		g.translate( -position.x * getZoom( ), -position.y * getZoom( ) );
		g.scale( getZoom( ), getZoom( ) );
	}

	public void endRender ( Graphics g )
	{
		// TODO PopMatrix and +Translate
		g.translate( position.x, position.y );
		g.scale( 1.f / getZoom( ), 1.f / getZoom( ) );
		g.popTransform( );
	}

	public void follow ( BaseUnit unit )
	{
		this.follow = unit;
	}

}
