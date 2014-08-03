import java.awt.Color;

/*
 * Define o comportamento do Enemy2
 */

public class Enemy2 extends Enemy {
	
	static double previousY;
	

	Enemy2(String id, Vector position) {
		super(id, position);
		this.state = ACTIVE;
		this.radius = 12.0;
		this.velocity = 0.42;
		this.angle = (3 * Math.PI) / 2;
		this.rotationvelocity = 0.0;
	}
	
	public void onCollision(GameActor collider)
	{
		switch(collider.getId())
		{
		case "PlayerProjectile":
		case "Player":
			if(this.state == ACTIVE)
			{
				this.explosion_start = Time.getCurrentTime();
				this.explosion_end = Time.getCurrentTime() + 500;
				this.state = EXPLODING;
			}
			break;
		
		}
	}
	
	public void update()
	{
		if(this.state == EXPLODING)
		{
			if(Time.getCurrentTime() > this.explosion_end)
			{
				GameController.Instance().Destroy(this);
			}
		}
		else
		{
			if(this.position.x < -10 || this.position.x > GameLib.WIDTH +10)
			{
				GameController.Instance().Destroy(this);
			}
			else{
				boolean shotNow = false;
				Enemy2.previousY= this.position.y;
				
				
				this.position.x += this.velocity * Math.cos(this.angle) * Time.deltaTime();
				this.position.y += this.velocity * Math.sin(this.angle) * Time.deltaTime() * (-1.0);
				this.angle += this.rotationvelocity * Time.deltaTime();
				
				double threshold = GameLib.HEIGHT * 0.30;
				
				if(Enemy2.previousY < threshold && this.position.y >= threshold) {
					
					if(this.position.x < GameLib.WIDTH / 2) this.rotationvelocity = 0.003;
					else this.rotationvelocity = -0.003;
				}
				
				if(this.rotationvelocity > 0 && Math.abs(this.angle - 3 * Math.PI) < 0.05){
					
					this.rotationvelocity = 0.0;
					this.angle = 3 * Math.PI;
					shotNow = true;
				}
				
				if(this.rotationvelocity < 0 && Math.abs(this.angle) < 0.05){
					
					this.rotationvelocity = 0.0;
					this.angle = 0.0;
					shotNow = true;
				}
																
				if(shotNow){

					double [] angles = { Math.PI/2 + Math.PI/8, Math.PI/2, Math.PI/2 - Math.PI/8 };
					//int [] freeArray = findFreeIndex(e_projectile_states, angles.length);
					
					for(int k = 0; k < 3; k++){
						
						//int free = freeArray[k];
						
						//if(free < e_projectile_states.length){
							
							double a = angles[k] + Math.random() * Math.PI/6 - Math.PI/12;
							double vx = Math.cos(a) * 0.5;
							double vy = Math.sin(a) * 0.5;
							
							
							Vector position = new Vector(this.position.x, this.position.y);
							Projectile novo = new Projectile("Projectile", position, vx, vy);
						//}
					}
				}
			}
		}
	}

	public void draw()
	{

		if(this.state == EXPLODING){
			
			double alpha = (Time.getCurrentTime() - explosion_start) / (explosion_end - explosion_start);
			while(alpha > 1.0)
				alpha -= 0.01;
			//System.out.println("X:"+this.position.x + "\t Y:" + this.position.y + "\t Y:" + alpha);
			GameLib.drawExplosion(this.position.x, this.position.y, alpha);
		}
		
		if(this.state == ACTIVE){
	
			GameLib.setColor(Color.MAGENTA);
			GameLib.drawDiamond(this.position.x, this.position.y, this.getRadius());
		}
	}
}
