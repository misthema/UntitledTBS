package worldgen;

import worldgen.WorldGenerator.LandType;

public class WorldManager
{

	WorldGenerator worldgen;

	public WorldManager()
	{

	}

	public void init ( )
	{
		worldgen = new WorldGenerator( 256, 256 );
		worldgen.setSeed( 256 );
		worldgen.setType( LandType.STANDARD );
		worldgen.create( );
	}

	public void init ( int w, int h, long seed, LandType type )
	{
		worldgen = new WorldGenerator( w, h );
		worldgen.setSeed( seed );
		worldgen.setType( type );
		worldgen.create( );
	}

}
