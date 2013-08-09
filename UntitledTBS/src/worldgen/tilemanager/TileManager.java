package worldgen.tilemanager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class TileManager
{

	private HashMap<Integer, Tile>  tiles;
	private HashMap<Integer, Float> noiseData;
	private TileSet                 tileset;
	private int                     worldWidth, worldHeight;
	private boolean                 showNoise;
	private Graphics                g;

	/**
	 * Create new TileManager.
	 */
	public TileManager(Graphics g)
	{
		this.tiles = new HashMap<Integer, Tile>( );
		this.tileset = new TileSet( "res/gfx/tiles.png", 32, 32 );
		this.worldWidth = 0;
		this.worldHeight = 0;
		this.showNoise = false;

		this.g = g;
		this.noiseData = new HashMap<Integer, Float>( );
	}

	/* @formatter:off */
	public int getWorldWidth( ) { return this.worldWidth; }
	public int getWorldHeight( ) { return this.worldHeight; }
	public void setWorldWidth( int w ) { this.worldWidth = w; }
	public void setWorldHeight( int h ) { this.worldHeight = h; }
	/* @formatter:on */

	/**
	 * Add Tile
	 * 
	 * @param tile
	 *            Tile to be added
	 */
	public void add ( Tile tile )
	{
		// Tile
		int x = tile.getX( );
		int y = tile.getY( );
		int w = getWorldWidth( );
		int id = y * w + x;

		tiles.put( id, tile );
	}

	public void addNoise ( int x, int y, Float h )
	{
		int w = getWorldWidth( );
		int id = y * w + x;

		// Noise data
		noiseData.put( id, h );
	}

	/**
	 * Get Tile from index
	 * 
	 * @param index
	 *            List index
	 * @return Tile
	 */
	public Tile get ( int x, int y )
	{
		int w = getWorldWidth( );
		int id = y * w + x;

		return tiles.get( id );
	}

	public float getNoise ( int x, int y )
	{
		int w = getWorldWidth( );
		int id = y * w + x;

		if (noiseData.containsKey( id ))
		{
			return noiseData.get( id ).floatValue( );
		}

		return 0.0f;
	}

	public void initTileset ( )
	{

	}

	/* @formatter:off */
	/**
	 * Set to see the noise map data
	 */
	public void setShowNoiseData ( boolean bool ) { this.showNoise = bool; }
	
	/* @formatter:on */

	/**
	 * Render ALL tiles
	 */
	public void render ( )
	{

		int tileWidth = tileset.getTileWidth( );
		int tileHeight = tileset.getTileHeight( );

		Set<Entry<Integer, Tile>> set = tiles.entrySet( );
		Iterator<Entry<Integer, Tile>> i = set.iterator( );

		while (i.hasNext( ))
		{
			Entry<Integer, Tile> entry = i.next( );
			Tile tile = entry.getValue( );
			int x = tile.getX( ) * tileWidth;
			int y = tile.getY( ) * tileHeight;

			drawTile( tile, x, y );
		}
	}

	/**
	 * Render only partition of the map
	 * 
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 */
	public void render ( int startX, int startY, int width, int height )
	{
		// Limit render
		int endX = startX + width;
		int endY = startY + height;

		// Tile related
		int tileWidth = tileset.getTileWidth( );
		int tileHeight = tileset.getTileHeight( );

		// Tiles
		for (Tile tile : tiles.values( ))
		{
			int x = tile.getX( ) * tileWidth;
			int y = tile.getY( ) * tileHeight;

			if ((x + tileWidth >= startX && x < endX)
			        && (y + tileHeight >= startY && y < endY))
			{
				drawTile( tile, x, y );
			}
		}
	}

	public void drawTile ( Tile tile, int x, int y )
	{
		// Draw tile from tile-set according to tile ID.
		int tileID = tile.getType( ).toInt( );

		if (!showNoise)
		{
			Image tileImg = tileset.getImage( tileID );

			tileImg.draw( x, y );

		} else
		{
			int tw = tileset.getTileWidth( );
			int th = tileset.getTileHeight( );
			Rectangle rect = new Rectangle( x * tw, y * th, tw, th );
			float c = this.getNoise( x, y ) * 255f;

			Color color = new Color( c, c, c );

			g.setColor( color );
			g.draw( rect );
		}
	}
}
