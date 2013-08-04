package worldgen;

import java.util.Random;

/*
 * 
 */
public class PerlinNoise
{

	private float[][] perlin;

	private int       octaves, w, h;
	private float     persistence;

	private Random    random;
	private long      seed;

	/*
	 * Constructor
	 */
	public PerlinNoise(long seed)
	{
		this.seed = seed;
		this.random = new Random( seed );
	}

	/* @formatter:off
	 * Get seed
	 */
	public long getSeed ( ) { return this.seed; }
	
	/*
	 * Sets the octaves for perlin
	 */
	public void setOctaves ( int octaves ) { this.octaves = octaves; }
	
	/*
	 * Sets the persistence for perlin
	 */
	public void setPersistence ( float persistence ) { this.persistence = persistence; }

	/*
	 * Sets the width
	 */
	public void setWidth ( int width ) { this.w = width; }
	
	/*
	 * Sets the height
	 */
	public void setHeight ( int height ) { this.h = height; }
	
	/*
	 * Returns the octaves set for perlin
	 */
	public int getOctaves ( ) { return this.octaves; }
	
	/*
	 * Returns the persistence set for perlin
	 */
	public float getPersistence ( ) { return this.persistence; }
	
	/*
	 * Returns the width
	 */
	public int getWidth ( ) { return this.w; }
	
	/*
	 * Returns the height
	 */
	public int getHeight ( ) { return this.h; }

	/* @formatter:on
	 * Creates random noise
	 */
	float[][] createBase ( )
	{

		float[][] base = new float[getWidth( )][getHeight( )];

		for (int j = 0; j < getHeight( ); j++)
		{
			for (int i = 0; i < getWidth( ); i++)
			{
				base[i][j] = xyFloat( i, j );
			}
		}

		return base;
	}

	/*
	 * Next float if not border
	 */
	private float xyFloat ( int x, int y )
	{

		if ((x > 16 && x < getHeight( ) - 16)
		        && (y > 16 && y < getWidth( ) - 16))
		{
			return random.nextFloat( );
		}

		return 0.0f;
	}

	/*
	 * Smoothen the base noise for specific octave
	 */
	float[][] createSmooth ( float[][] base, int octave )
	{

		// Init
		float[][] smooth = new float[getWidth( )][getHeight( )];
		int samplePeriod = 1 << octave;
		float sampleFrequency = 1.0f / samplePeriod;

		for (int i = 0; i < getWidth( ); i++)
		{

			// Calculate the horizontal sampling indices
			int sample_i0 = (i / samplePeriod) * samplePeriod;
			int sample_i1 = (sample_i0 + samplePeriod) % getWidth( ); // wrap
			// around
			float horizontal_blend = (i - sample_i0) * sampleFrequency;

			for (int j = 0; j < getHeight( ); j++)
			{

				// Calculate the vertical sampling indices
				int sample_j0 = (j / samplePeriod) * samplePeriod;
				int sample_j1 = (sample_j0 + samplePeriod) % getHeight( ); // wrap
				// around
				float vertical_blend = (j - sample_j0) * sampleFrequency;

				// Blend the top two corners
				float top = cosineInterpolate( base[sample_i0][sample_j0],
				        base[sample_i1][sample_j0], horizontal_blend );

				// Blend the bottom two corners
				float bottom = cosineInterpolate( base[sample_i0][sample_j1],
				        base[sample_i1][sample_j1], horizontal_blend );

				// Finally blend top with bottom
				smooth[i][j] = cosineInterpolate( top, bottom, vertical_blend );
			}
		}

		return smooth;
	}

	/*
	 * Generate the perlin noise itself
	 */
	void generatePerlin ( )
	{

		// White noise
		float[][] base = createBase( );

		// Smooth noises
		float[][][] smooth = new float[getOctaves( )][getWidth( )][getHeight( )];

		// Generate smooth noises from base
		for (int oct = 0; oct < getOctaves( ); oct++)
		{
			smooth[oct] = createSmooth( base, oct );
		}

		// Init
		perlin = new float[getWidth( )][getHeight( )];
		float amplitude = 1.0f;
		float totalAmplitude = 0.0f;

		// Blend smooth noises together
		for (int oct = getOctaves( ) - 1; oct >= 0; oct--)
		{

			amplitude *= getPersistence( );
			totalAmplitude += amplitude;

			for (int j = 0; j < getHeight( ); j++)
			{
				for (int i = 0; i < getWidth( ); i++)
				{
					perlin[i][j] += smooth[oct][i][j] * amplitude;
				}
			}
		}

		// Normalize
		for (int j = 0; j < getHeight( ); j++)
		{
			for (int i = 0; i < getWidth( ); i++)
			{
				perlin[i][j] /= totalAmplitude;
			}
		}

		// We are done!
	}

	/*
	 * Get float value from a point
	 */
	public float getFloat ( int x, int y )
	{
		return perlin[x][y];
	}

	/*
	 * Interpolate with cosine
	 */
	float cosineInterpolate ( float y1, float y2, float mu )
	{
		float mu2;

		mu2 = (float) ((1.0f - Math.cos( mu * Math.PI )) / 2.0f);
		return (y1 * (1.0f - mu2) + y2 * mu2);
	}

	/*
	 * Linear interpolation
	 */
	float linearInterpolate ( float v1, float v2, float a )
	{
		return v1 * (1.0f - a) + v2 * a;
	}
}
