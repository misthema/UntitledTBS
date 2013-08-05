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
			drawTile( tile, tile.getX( ) * 32, tile.getY( ) * 32 );
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
			int x = tile.getX( ) * 32;
			int y = tile.getY( ) * 32;

			if ((x > startX && x < endX) && (y > startY && y < endY))
			{
				drawTile( tile, x, y );
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
