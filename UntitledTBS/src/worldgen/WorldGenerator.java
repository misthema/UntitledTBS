package worldgen;

public class WorldGenerator
{
	public enum LandType
	{
		ISLANDIC, STANDARD, MOUNTAINS
	};

	private HeightMap islandParts;
	private HeightMap heightMap;
	private long      seed;
	private int       width, height;
	private LandType  type;

	public WorldGenerator(int width, int height)
	{
		// Size
		this.setWidth( width );
		this.setHeight( height );

		// Final map
		this.heightMap = new HeightMap( );

		// Island parts
		this.islandParts = new HeightMap( );
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
		this.initIslandParts( );

		// heightMap = islandParts;
		this.initHeightMap( );
		this.mergeHeightmaps( );
	}

	public void initIslandParts ( )
	{
		// Perlin
		PerlinNoise perlin = new PerlinNoise( getSeed( ) );
		perlin.setWidth( getWidth( ) );
		perlin.setHeight( getHeight( ) );

		// Island parts settings
		perlin.setOctaves( 4 );
		perlin.setPersistence( 0.3f );

		// Island parts
		islandParts.setPerlin( perlin );
		islandParts.setWaterLevel( 0.6f ); // 0.0f default level

		islandParts.build( );
	}

	public void initHeightMap ( )
	{
		// Perlin
		PerlinNoise perlin = new PerlinNoise( getSeed( ) );
		perlin.setWidth( getWidth( ) );
		perlin.setHeight( getHeight( ) );

		// Land type settings
		switch (type)
		{
			case ISLANDIC:
			{
				perlin.setOctaves( 4 );
				perlin.setPersistence( 0.4f );

				heightMap.setWaterLevel( 0.5f );
				break;
			}
			case STANDARD:
			{
				perlin.setOctaves( 7 );
				perlin.setPersistence( 0.5f );

				heightMap.setWaterLevel( 0.5f );
				break;
			}
			case MOUNTAINS:
			{
				perlin.setOctaves( 5 );
				perlin.setPersistence( 0.5f );

				heightMap.setWaterLevel( 0.3f );
				break;
			}
		}

		// Height map
		heightMap.setPerlin( perlin );

		heightMap.build( );
	}

	public void mergeHeightmaps ( )
	{
		for (int y = 0; y < getHeight( ); y++)
		{
			for (int x = 0; x < getWidth( ); x++)
			{
				float p1 = heightMap.heightmap[x][y];
				float p2 = islandParts.heightmap[x][y];
				float h = heightMap.getHeightValue( x, y );

				float p3 = blend( p1 * 255f, p2 * 255f, 0 );

				// if (h < -0.4)
				// {
				// p3 = blend( p1 * 255f, p2 * 255f, 0 );
				// }

				heightMap.heightmap[x][y] = p3 / 255f;
			}
		}
	}

	private float blend ( float c1, float c2, int type )
	{
		float c = 0.f;

		switch (type)
		{
			case 0:
			{ // Overlay
				if (c2 < 128.0)
				{
					c = 2 * c1 * c2 / 255.f;
				} else
				{
					c = (255.f - 2 * (255.f - c1) * (255.f - c2) / 255.f);
				}
				break;
			}
			case 1:
			{ // Lighten
				c = Math.max( c1, c2 );
				break;
			}
			case 2:
			{ // Darken
				c = Math.min( c1, c2 );
			}
			case 3:
			{ // Exclusion
				c = c1 + c2 - c1 * c2 / 255.f;
				break;
			}
			case 4:
			{ // Multiply
				c = c1 * c2 / 255.f;
				break;
			}
		}

		return c;
	}

}
