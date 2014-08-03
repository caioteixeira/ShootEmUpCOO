
public abstract class Enemy extends GameActor {
	
	/*
	 * Classe abstrata que define variáveis comuns a inimigos
	 */
	
	
	//Estados
	public static final int INACTIVE = 0;
	public static final int ACTIVE = 1;
	public static final int EXPLODING = 2;
	
	public double velocity;
	public double rotationvelocity;
	public double angle;
	
	double explosion_start;
	double explosion_end;
	
	int state;
	
	Enemy(String id, Vector position) {
		super(id, position);
	}
}
