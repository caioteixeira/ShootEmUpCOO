
public abstract class Enemy extends GameActor {
	
	//Estados
	public static final int INACTIVE = 0;
	public static final int ACTIVE = 1;
	public static final int EXPLODING = 2;
	
	double velocity;
	double rotationvelocity;
	double angle;
	
	double explosion_start;
	double explosion_end;
	
	int state = INACTIVE;
	
	Enemy(String id, Vector position, int radius) {
		super(id, position, radius);
	}
}
