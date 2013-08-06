package worldgen;

import worldgen.WorldGenerator.LandType;
import worldgen.tilemanager.Tile;
import worldgen.tilemanager.Tile.TileType;
import worldgen.tilemanager.TileManager;

public class WorldManager
{

	private WorldGenerator           worldgen;
	private TileManager              tileManager;
	private RandomLandType<LandType> randLandType = new RandomLandType<LandType>(
	                                                      LandType.class );

	/**
	 * Create new WorldManager.
	 */
	public WorldManager()
	{

	}

	/* @formatter:off */
	public TileManager getTileManager( ) { return this.tileManager; }
	public WorldGenerator getWorldGen( ) { return this.worldgen; }
	public void setTileManager( TileManager tileManager ) { this.tileManager = tileManager;	}
	public void setWorldGen( WorldGenerator worldgen ) { this.worldgen = worldgen; } 
	/* @formatter:on */

	/**
	 * Initialize WorldManager with default size, seed and random land type.
	 */
	public void init ( )
	{
		assert (tileManager == null) : "WorldManager.tileManager: not set!";
		tileManager.setWorldWidth( 256 );
		tileManager.setWorldHeight( 256 );

		if (worldgen == null) worldgen = new WorldGenerator( 256, 256 );
		worldgen.setSeed( (long) (Math.random( ) * 100000) );
		worldgen.setType( LandType.ISLANDIC );// randLandType.random( )
		worldgen.create( );

		initTileManager( );
	}

	/**
	 * Initialize WorldManager with size, seed and land type.
	 * 
	 * @param w
	 * @param h
	 * @param seed
	 * @param type
	 */
	public void init ( int w, int h, long seed, LandType type )
	{
		assert (tileManager == null) : "WorldManager.tileManager: not set!";
		tileManager.setWorldWidth( w );
		tileManager.setWorldHeight( h );

		worldgen = new WorldGenerator( w, h );
		worldgen.setSeed( seed );
		worldgen.setType( type );
		worldgen.create( );

		initTileManager( );
	}

	public void initTileManager ( )
	{
		// TODO Better system for deciding a tile for specific height.

		HeightMap hmap = worldgen.getHeightMap( );

		System.out.println( "world size " + worldgen.getWidth( ) + ", "
		        + worldgen.getHeight( ) );

		for (int y = 0; y < worldgen.getHeight( ); y++)
		{
			for (int x = 0; x < worldgen.getWidth( ); x++)
			{

				System.out.print( "getHeight( " + x + ", " + y + " ) = " );

				float height = hmap.getHeightValue( x, y );
				TileType tt = null;

				System.out.println( height );

				if (height >= -1.0 && height < -0.7)
				{
					tt = TileType.WATER_DEEP;

				} else if (height >= -0.7 && height < -0.6)
				{
					tt = TileType.WATER_AVERAGE;

				} else if (height >= -0.6 && height < -0.5)
				{
					tt = TileType.WATER_SHALLOW;

				} else if (height >= -0.5 && height < -0.33)
				{
					tt = TileType.LAND_SHORE;

				} else if (height >= -0.33 && height < 0.33)
				{
					tt = TileType.LAND_GRASS;

				} else if (height >= 0.33 && height <= 1.0)
				{
					tt = TileType.LAND_MOUNTAIN;

				}

				tileManager.add( new Tile( x, y, tt ) );

			}
		}
	}
}
