package worldgen.tilemanager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.newdawn.slick.Image;

public class TileManager
{

	private HashMap<Integer, Tile> tiles;
	private TileSet                tileset;
	private int                    worldWidth, worldHeight;

	/**
	 * Create new TileManager.
	 */
	public TileManager()
	{
		this.tiles = new HashMap<Integer, Tile>( );
		this.tileset = new TileSet( "res/gfx/tiles.png", 32, 32 );
		worldWidth = 0;
		worldHeight = 0;
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
		int x = tile.getX( );
		int y = tile.getY( );
		int w = getWorldWidth( );
		int id = y * w + x;

		tiles.put( id, tile );
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

	public void initTileset ( )
	{

	}

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
		startX /= 32;
		startY /= 32;
		width /= 32;
		height /= 32;

		int endX = startX + width;
		int endY = startY + height;
		int tileWidth = tileset.getTileWidth( );
		int tileHeight = tileset.getTileHeight( );

		for (int y = startY; y < endY; y++)
		{
			for (int x = startX; x < endX; x++)
			{
				Tile tile = get( x, y );

				if (tile != null)
				{
					int tx = tile.getX( ) * tileWidth;
					int ty = tile.getY( ) * tileHeight;

					drawTile( tile, tx, ty );
				}
			}
		}
	}

	public void drawTile ( Tile tile, int x, int y )
	{
		// Draw tile from tile-set according to tile ID.
		int tileID = tile.getType( ).toInt( );

		Image tileImg = tileset.getImage( tileID );

		tileImg.draw( x, y );
	}
}
