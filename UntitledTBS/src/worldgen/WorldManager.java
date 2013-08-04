package worldgen;

import worldgen.WorldGenerator.LandType;

public class WorldManager
{

	WorldGenerator worldgen;
	
	public WorldManager ( ) {
		worldgen = new WorldGenerator( 256, 256 );
		worldgen.setSeed( 256 );
		worldgen.setType( LandType.STANDARD );
		worldgen.create( );
	}
	
	
}
