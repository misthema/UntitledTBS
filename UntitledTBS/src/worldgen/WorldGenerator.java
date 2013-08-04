package worldgen;

public class WorldGenerator
{
	public enum LandType
	{
		ISLANDIC, STANDARD, MOUNTAINS
	};

	private HeightMap world;
	private long      seed;
	private int       width, height;
	private LandType  type;

	public WorldGenerator(int width, int height)
	{
		this.world = new HeightMap( );
		this.setWidth( width );
		this.setHeight( height );
	}

	/* @formatter:off */
	public void     setType ( LandType type ) { this.type = type; }
	public LandType getType ( ) { return this.type; }
	public void     setWidth ( int width ) { this.width = width; }
	public int      getWidth ( ) { return this.width; }
	public void     setHeight ( int height ) { this.height = height; }
	public int      getHeight ( ) { return this.height; }
	public void     setSeed ( long seed ) { this.seed = seed; }
	public long     getSeed ( ) { return this.seed; }
	/* @formatter:on */

	public void create ( )
	{

		PerlinNoise perlin = new PerlinNoise( getSeed( ) );
		perlin.setWidth( getWidth( ) );
		perlin.setHeight( getHeight( ) );

		world.setPerlin( perlin );
		world.setWaterLevel( 0.0f ); // 0.0f default level

		switch (type)
		{
			case ISLANDIC:
			{
				perlin.setOctaves( 5 );
				perlin.setPersistence( 0.5f );
			}
			case STANDARD:
			{
				perlin.setOctaves( 7 );
				perlin.setPersistence( 0.4f );
			}
			case MOUNTAINS:
			{
				perlin.setOctaves( 8 );
				perlin.setPersistence( 0.5f );
			}
		}
	}

	public void mergeHeightmaps ( )
	{
		for (int y = 0; y < getHeight( ); y++)
		{
			for (int x = 0; x < getWidth( ); x++)
			{
				// FIXME
			}
		}
	}

}
