package worldgen;

import java.util.Random;

public class RandomLandType<E extends Enum>
{
	private static final Random r = new Random( );
	private final E[]           values;

	/**
	 * Create new RandomLandType.
	 * 
	 * @param token
	 */
	public RandomLandType(Class<E> token)
	{
		values = token.getEnumConstants( );
	}

	/**
	 * Pick a random land type
	 * 
	 * @return WorldGenerator.LandType
	 */
	public E random ( )
	{
		return values[r.nextInt( values.length )];
	}

}
