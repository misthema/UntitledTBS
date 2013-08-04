package component;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Camera {

	Vector2f position;
	
	float zoom, scale;
	
	BaseUnit follow;
	
	public Camera (float x, float y) {
		this.position = new Vector2f( x, y );
	}
	
	public void startRender (Graphics g) {
		// TODO PushMatrix and -Translate
		g.pushTransform();
    	g.translate( -position.x * zoom, -position.y * zoom );
    	g.scale( zoom, zoom );
	}
	
	public void endRender (Graphics g) {
		// TODO PopMatrix and +Translate
		g.translate( position.x, position.y );
    	g.scale( 1.f / zoom, 1.f / zoom );
    	g.popTransform();
	}
	
	public void follow (BaseUnit unit) {
		this.follow = unit;
	}

}
