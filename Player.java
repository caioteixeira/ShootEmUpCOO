import java.awt.Color;


/*
 * Define compartamento do objeto Player
 * 
 * � um Singleton, pois precisa ser o �nico objeto deste tipo no jogo.
 */

public class Player extends GameActor {
	public static final int ACTIVE = 1;
	public static final int EXPLODING = 2;
	
	private int state;
	
	double VX = 0.25;								// velocidade no eixo x
	double VY = 0.25;								// velocidade no eixo y	
	double explosion_start = 0;						// instante do início da explosão
	double explosion_end = 0;						// instante do final da explosão
	long nextShot;									// instante a partir do qual pode haver um próximo tiro

	private Player(String id, Vector position) {
		super(id, position);
		state = ACTIVE;
		nextShot = Time.getCurrentTime();
		this.radius = 12.0;
	}
	
	//Implementa Singleton
	private static Player instance;
	public static Player Instance()
	{
		if(Player.instance == null)
			Player.instance = new Player("Player", new Vector(GameLib.WIDTH /2, GameLib.HEIGHT * 0.90));
		return Player.instance;
	}
	
	public void onCollision(GameActor collider)
	{
		String id = collider.id;
		if(this.state == ACTIVE)
		{
			switch(id)
			{
			case "Projectile":
			case "Enemy1":
			case "Enemy2":
			case "Enemy3":
				state = EXPLODING;
				explosion_start = Time.getCurrentTime();
				explosion_end = Time.getCurrentTime() + 2000;
				break;
			}
		}
	}
	
	public void update()
	{
		if(state == EXPLODING)
		{
			if(Time.getCurrentTime() > explosion_end){
				
				state = ACTIVE;
			}
		}
		else{
			if(GameLib.iskeyPressed(GameLib.KEY_UP)) position.y -= Time.deltaTime() * this.VY;
			if(GameLib.iskeyPressed(GameLib.KEY_DOWN)) position.y += Time.deltaTime() * VY;
			if(GameLib.iskeyPressed(GameLib.KEY_LEFT)) position.x -= Time.deltaTime() * VX;
			if(GameLib.iskeyPressed(GameLib.KEY_RIGHT)) position.x += Time.deltaTime() * VY;
			if(GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {
				
				//System.out.println("CTRL");
				if(Time.getCurrentTime() > this.nextShot){		
					
					Projectile novo = new PlayerProjectile("PlayerProjectile", new Vector(this.position.x, this.position.y - 2 * this.radius), 0.0, -1.0);
					
					this.nextShot = Time.getCurrentTime() + 100;
						/*projectile_X[free] = player_X;
						projectile_Y[free] = player_Y - 2 * player_radius;
						projectile_VX[free] = 0.0;
						projectile_VY[free] = -1.0;
						projectile_states[free] = 1;*/
						
					
				}	
			}
		}
		
		if(this.position.x < 0.0) this.position.x = 0.0;
		if(this.position.x >= GameLib.WIDTH) this.position.x = GameLib.WIDTH - 1;
		if(this.position.y < 25.0) this.position.y = 25.0;
		if(this.position.y >= GameLib.HEIGHT) this.position.y = GameLib.HEIGHT - 1;
	}
	
	public void draw()
	{
		if(state == EXPLODING){
			
			double alpha = (Time.getCurrentTime() - explosion_start) / (explosion_end - explosion_start);
			GameLib.drawExplosion(this.position.x, this.position.y, alpha);
		}
		else{
			
			GameLib.setColor(Color.BLUE);
			GameLib.drawPlayer(this.position.x, this.position.y, this.radius);
		}
	}

}
