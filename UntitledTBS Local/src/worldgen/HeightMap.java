package worldgen;

public class HeightMap
{
	private float[][]   heightmap;

	private float       waterLevel;

	private PerlinNoise perlin;

	/*
	 * Constructor
	 */
	public HeightMap()
	{
		this.waterLevel = 0.f;
	}

	/* @formatter:off
	 * Set perlin noise for heightmap
	 */
	public void setPerlin ( PerlinNoise perlin ) { this.perlin = perlin; }
	
	/*
	 * Get perlin noise
	 */
	public PerlinNoise getPerlin ( ) { return this.perlin; }

	/*
	 * Returns the water level
	 */
	public void setWaterLevel ( float waterLevel ) { this.waterLevel = waterLevel; }

	/*
	 * Returns the water level
	 */
	public float getWaterLevel ( ) { return this.waterLevel; }

	/* @formatter:on
	 * Build the height map
	 */
	public void build ( )
	{
		// Generate perlin noise
		perlin.generatePerlin( );

		// Init height map
		this.heightmap = new float[perlin.getWidth( )][perlin.getHeight( )];

		for (int y = 0; y < perlin.getHeight( ); y++)
		{
			for (int x = 0; x < perlin.getWidth( ); x++)
			{
				// Height
				this.heightmap[x][y] = this.getHeight( perlin.getFloat( x, y ) );
			}
		}

	}

	/*
	 * Convert perlin noise to useful height value between -1.0 - 1.0.
	 */
	public float getHeight ( float val )
	{
		float height = val * 2.f - 1.f;
		return clamp( lerp( height, -1.f, waterLevel ) );
	}

	/*
	 * Clamp for height value
	 */
	private static float clamp ( float val )
	{
		if (val > 1.f) return 1.f;
		if (val < -1.f) return -1.f;
		return val;
	}

	/*
	 * Linear interpolation
	 */
	private static float lerp ( float v1, float v2, float a )
	{
		return v1 * (1.0f - a) + v2 * a;
	}
}
