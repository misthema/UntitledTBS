package worldgen.tilemanager;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class TileSet
{
	private SpriteSheet tiles;
	private int         width, height, tileWidth, tileHeight;

	/**
	 * Create new tile-set from an image file.
	 * 
	 * @param url
	 *            String
	 */
	public TileSet(String url, int tileWidth, int tileHeight)
	{
		try
		{
			this.tiles = new SpriteSheet( url, 32, 32 );
			this.width = tiles.getWidth( ) / tileWidth;
			this.height = tiles.getHeight( ) / tileHeight;
			this.tileWidth = tileWidth;
			this.tileHeight = tileHeight;
		} catch (Exception e)
		{
			e.printStackTrace( );
		}
	}

	/* @formatter:off */
	public int 		getWidth ( ) { return this.width; }
	public int 		getHeight ( ) { return this.height; }
	public int 		getTileWidth ( ) { return this.tileWidth; }
	public int		getTileHeight( ) { return this.tileHeight; }
	/* @formatter:on */

	/**
	 * Create new tile-set from a loaded image.
	 * 
	 * @param image
	 *            Image
	 */
	public TileSet(Image image, int tileWidth, int tileHeight)
	{
		this.tiles = new SpriteSheet( image, tileWidth, tileHeight );
	}

	/**
	 * Get tile image with tile coordinates.
	 * 
	 * @param x
	 * @param y
	 * @return Image
	 */
	public Image getImage ( int ID )
	{
		int x = ID % getWidth( );
		int y = ID / getWidth( );

		if (x >= 0 && x < getWidth( ) && y >= 0 && y < getHeight( ))
		{
			return tiles.getSubImage( x, y );
		}

		return null;
	}
}
