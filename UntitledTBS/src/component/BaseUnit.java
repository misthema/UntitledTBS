package component;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;

/**
 * The base unit with basic methods.
 * 
 * @author Jani
 * 
 */
public class BaseUnit
{

	Vector2f position;
	Image    image;
	Shape    basehit, hit;
	float    angle;

	/**
	 * Creates a new unit at the top left position with blank image.
	 * 
	 * @throws SlickException
	 */
	public BaseUnit() throws SlickException
	{
		this.init( new Image( 2, 2 ), new Vector2f( 0, 0 ), new Rectangle( 0,
		        0, 1, 1 ), 0.0f );
	}

	/**
	 * Creates a new unit at the top left position. Loads image from given path.
	 * 
	 * @param uri
	 *            The path to load the image from.
	 * @throws SlickException
	 */
	public BaseUnit(String uri) throws SlickException
	{
		Image img = new Image( uri );
		this.init( img, new Vector2f( 0, 0 ),
		           new Rectangle( 0, 0, img.getWidth( ), img.getHeight( ) ),
		           0.0f );
	}

	/**
	 * Internal initializer for BaseUnit.
	 * 
	 * @param image
	 *            The image used as the sprite of the unit.
	 * @param pos
	 *            Position the unit will start at.
	 * @param hit
	 *            Shape for hit detection.
	 * @param angle
	 *            Angle the unit will start at.
	 */
	private void init ( Image image, Vector2f pos, Shape hit, float angle )
	{
		this.image = image;
		this.position = pos;
		this.basehit = hit;
		this.angle = angle;

		// Move the shape to corresponding location.
		this.basehit.setLocation( pos.x + hit.getX( ), pos.y + hit.getY( ) );

		updateHit( );
	}

	/**
	 * Draws the unit at it's current position and rotation.
	 */
	public void draw ( )
	{
		image.draw( position.x, position.y );
	}

	/**
	 * Rotates the unit with given angle.
	 * 
	 * @param ang
	 *            Angle in degrees.
	 */
	public void rotate ( float ang )
	{
		angle += ang;
		angle %= 360;
		if (angle < 0) angle += 360;

		image.rotate( -ang );

		updateHit( );
	}

	/**
	 * Translates the unit with given vector.
	 * 
	 * @param vec
	 *            Vector to translate the unit with.
	 */
	public void translate ( Vector2f vec )
	{
		this.basehit.setLocation( this.basehit.getX( ) + vec.x,
		                          this.basehit.getY( ) + vec.y );
		this.position.add( vec );

		updateHit( );
	}

	/**
	 * Translates the unit with given vector.
	 * 
	 * @param x
	 * @param y
	 */
	public void translate ( double x, double y )
	{
		translate( new Vector2f( (float) x, (float) y ) );
	}

	/**
	 * Move the unit forward at current angle.
	 * 
	 * @param speed
	 *            Distance to move.
	 */
	public void move ( float speed )
	{
		translate( Math.cos( getReversedRadAngle( ) ) * speed,
		           Math.sin( getReversedRadAngle( ) ) * speed );
	}

	public boolean hits ( BaseUnit unit )
	{
		return hit.intersects( unit.getHit( ) )
		        || unit.getHit( ).contains( hit );
	}

	/**
	 * Updates the rotated hitshape to match current rotation and location.
	 */
	private void updateHit ( )
	{
		hit = basehit.transform( Transform
		        .createRotateTransform( getReversedRadAngleF( ), basehit.getX( )
		                                        + (basehit.getWidth( ) / 2),
		                                basehit.getY( )
		                                        + (basehit.getHeight( ) / 2) ) );
	}

	/* ##### Getters ##### */
	/* @formatter:off */

	public  Shape              getHit() { return this.hit; }
	public double         getRadAngle() { return Math.toRadians(this.angle); }
	public float         getRadAngleF() { return (float) getRadAngle(); }
	public double getReversedRadAngle() { return Math.toRadians(-this.angle); }
	public float getReversedRadAngleF() { return (float) getReversedRadAngle(); }
	
	/* @formatter:on */

}
