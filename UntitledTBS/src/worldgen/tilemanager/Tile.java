package worldgen.tilemanager;

public class Tile
{

	public enum TileType
	{
		WATER_DEEP, WATER_SHALLOW, LAND_SHORE, LAND_GRASS, LAND_MOUNTAIN
	}

	float    x, y;

	TileType tileType;

	/* @formatter:off */
	public void setX ( float x ) { this.x = x; }
	public float getX ( ) { return this.x; }
	public void setY ( float y ) { this.y = y; }
	public float getY ( ) { return this.y; }
	/* @formatter:on */

}
