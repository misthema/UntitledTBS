package worldgen.tilemanager;

/**
 * Tile class
 * 
 * @author misthema
 * 
 */
public class Tile
{

	/**
	 * TileType enum class
	 * 
	 * @author misthema
	 * 
	 */
	public enum TileType
	{
		/* @formatter:off */
		WATER_DEEP		( 2, 8 ),
		WATER_AVERAGE   ( 1, 8 ),
		WATER_SHALLOW	( 0, 8 ),
		LAND_SHORE		( 3, 1 ),
		LAND_GRASS		( 0, 1 ),
		LAND_MOUNTAIN	( 0, 13 ),
		LAND_FOREST11   ( 0, 6 ),
		LAND_FOREST12   ( 3, 6 ),
		LAND_FOREST13   ( 6, 6 );
		/* @formatter:on */

		private final int ID;

		private TileType(int x, int y)
		{
			this.ID = y * 9 + x;
		}

		public int toInt ( )
		{
			return this.ID;
		}
	}

	int      x, y;
	int      ID;
	TileType tileType;

	/**
	 * Creates new tile with given input.
	 * 
	 * @param id
	 * @param x
	 * @param y
	 * @param tt
	 *            TileType enumerator
	 */
	public Tile(int x, int y, TileType tt)
	{
		// this.ID = id;
		this.x = x;
		this.y = y;
		this.tileType = tt;
	}

	/* @formatter:off */
	public int 		getX ( ) { return this.x; }
	public int 		getY ( ) { return this.y; }
	public TileType getType ( ) { return this.tileType; }
	public void 	setX ( int x ) { this.x = x; }
	public void 	setY ( int y ) { this.y = y; }
	public void		setType ( TileType tt ) { this.tileType = tt; }
	/* @formatter:on */

}
