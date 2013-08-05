package worldgen.tilemanager;

import java.util.ArrayList;

import org.newdawn.slick.Image;

public class TileManager
{

	private ArrayList<Tile> tiles;
	private TileSet         tileset;

	/**
	 * Create new TileManager.
	 */
	public TileManager()
	{
		this.tiles = new ArrayList<Tile>( );
		this.tileset = new TileSet( "res/gfx/tiles.png", 32, 32 );
	}

	/**
	 * Add Tile
	 * 
	 * @param tile
	 *            Tile to be added
	 */
	public void add ( Tile tile )
	{
		tiles.add( tile );
	}

	/**
	 * Get Tile from index
	 * 
	 * @param index
	 *            List index
	 * @return Tile
	 */
	public Tile get ( int index )
	{
		return tiles.get( index );
	}

	public void initTileset ( )
	{

	}

	/**
	 * Render ALL tiles
	 */
	public void render ( )
	{

		for (Tile tile : tiles)
		{
			drawTile( tile );
		}
	}

	/**
	 * Render only partion of the map
	 * 
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 */
	public void render ( int startX, int startY, int endX, int endY )
	{
		for (Tile tile : tiles)
		{
			int x = tile.getX( );
			int y = tile.getY( );

			if ((x > startX && x < endX) && (y > startY && y < endY))
			{
				drawTile( tile );
			}
		}
	}

	public void drawTile ( Tile tile )
	{
		// Draw tile from tile-set according to tile ID.
		int tileID = tile.getType( ).toInt( );

		Image tileImg = tileset.getImage( tileID );

		int x = tile.getX( );
		int y = tile.getY( );

		tileImg.draw( x, y );
	}
}
