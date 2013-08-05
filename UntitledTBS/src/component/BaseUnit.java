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
	Image image;
	Shape basehit;
	float angle;

	/**
	 * Creates a new unit at the top left position with blank image.
	 * 
	 * @throws SlickException
	 */
	public BaseUnit() throws SlickException
	{
		this.init(new Image(2, 2),
		          new Vector2f(0, 0),
		          new Rectangle(0, 0, 1, 1),
		          0.0f);
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
		Image img = new Image(uri);
		this.init(img,
		          new Vector2f(0, 0),
		          new Rectangle(0, 0, img.getWidth(), img.getHeight()),
		          0.0f);
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
	private void init(Image image, Vector2f pos, Shape hit, float angle)
	{
		this.image = image;
		this.position = pos;
		this.basehit = hit;
		this.angle = angle;

		// Move the shape to corresponding location.
		this.basehit.setLocation(pos.x + hit.getX(),
		                         pos.y + hit.getY());
	}

	/**
	 * Draw the unit at it's current position and rotation.
	 */
	public void draw()
	{
		image.draw(position.x,
		           position.y);
	}

	/* ##### Getters ##### */
	/* @formatter:off */

	public Shape getHit() { return this.basehit; }
	
	/* @formatter:on */

}
