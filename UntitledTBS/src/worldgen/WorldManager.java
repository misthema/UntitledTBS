package worldgen;

import worldgen.WorldGenerator.LandType;
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

	/**
	 * Initialize WorldManager with default size, seed and random land type.
	 */
	public void init ( )
	{
		assert (tileManager == null) : "WorldManager.tileManager: not set!";

		if (worldgen == null) worldgen = new WorldGenerator( 256, 256 );
		worldgen.setSeed( 256 );
		worldgen.setType( randLandType.random( ) );
		worldgen.create( );
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

		worldgen = new WorldGenerator( w, h );
		worldgen.setSeed( seed );
		worldgen.setType( type );
		worldgen.create( );
	}

	/* @formatter:off */
	public TileManager getTileManager( ) { return this.tileManager; }
	public WorldGenerator getWorldGen( ) { return this.worldgen; }
	public void setTileManager( TileManager tileManager ) { this.tileManager = tileManager;	}
	public void setWorldGen( WorldGenerator worldgen ) { this.worldgen = worldgen; } 
	/* @formatter:on */
}
