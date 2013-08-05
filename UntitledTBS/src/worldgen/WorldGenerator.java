package worldgen;

public class WorldGenerator
{
	public enum LandType
	{
		ISLANDIC, STANDARD, MOUNTAINS
	};

	private HeightMap heightMap;
	private long      seed;
	private int       width, height;
	private LandType  type;

	public WorldGenerator(int width, int height)
	{
		this.heightMap = new HeightMap( );
		this.setWidth( width );
		this.setHeight( height );
	}

	/* @formatter:off */
	public HeightMap getHeightMap( ) { return this.heightMap; }
	
	public LandType  getType ( ) { return this.type; }
	public int       getWidth ( ) { return this.width; }
	public int       getHeight ( ) { return this.height; }
	public long      getSeed ( ) { return this.seed; }
	public void      setType ( LandType type ) { this.type = type; }
	public void      setWidth ( int width ) { this.width = width; }
	public void      setHeight ( int height ) { this.height = height; }
	public void      setSeed ( long seed ) { this.seed = seed; }
	/* @formatter:on */

	public void create ( )
	{

		PerlinNoise perlin = new PerlinNoise( getSeed( ) );
		perlin.setWidth( getWidth( ) );
		perlin.setHeight( getHeight( ) );

		switch (type)
		{
			case ISLANDIC:
			{
				perlin.setOctaves( 5 );
				perlin.setPersistence( 0.5f );
				break;
			}
			case STANDARD:
			{
				perlin.setOctaves( 7 );
				perlin.setPersistence( 0.4f );
				break;
			}
			case MOUNTAINS:
			{
				perlin.setOctaves( 8 );
				perlin.setPersistence( 0.5f );
				break;
			}
		}

		heightMap.setPerlin( perlin );
		heightMap.setWaterLevel( 0.0f ); // 0.0f default level

		heightMap.build( );
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
