import java.awt.Color;


public class Enemy1 extends Enemy {
	
	long nextShot;

	Enemy1(String id, Vector position) {
		super(id, position);
		state = ACTIVE;
		nextShot = (long) (Time.getCurrentTime() + 500);
		this.radius = 9.0;
	}
	
	
	public void onCollision(GameActor collider)
	{
		switch(collider.getId())
		{
		case "PlayerProjectile":
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
		switch(this.state)
		{
		case EXPLODING:
			if(Time.getCurrentTime() > explosion_end){
				
				this.state = INACTIVE;
			}
			break;
		case ACTIVE:
			/* verificando se inimigo saiu da tela */
			if(this.position.y > GameLib.HEIGHT + 10) {
				
				GameController.Instance().Destroy(this);
			}
			else {
				
				/*
				 * enemy1_X[i] += enemy1_V[i] * Math.cos(enemy1_angle[i]) * delta;
				 * enemy1_Y[i] += enemy1_V[i] * Math.sin(enemy1_angle[i]) * delta * (-1.0);
				 * enemy1_angle[i] += enemy1_RV[i] * delta;
				 */
				
				//Atualiza posição
				//System.out.println(this.velocity * Math.cos(this.angle) * Time.deltaTime());
				this.position.x += this.velocity * Math.cos(this.angle) * Time.deltaTime();
				this.position.y += this.velocity * Math.sin(this.angle) * Time.deltaTime() * (-1.0);
				this.angle += this.rotationvelocity * Time.deltaTime();
			
				/*
				 * 
				 * e_projectile_X[free] = enemy1_X[i];
					e_projectile_Y[free] = enemy1_Y[i];
					e_projectile_VX[free] = Math.cos(enemy1_angle[i]) * 0.45;
					e_projectile_VY[free] = Math.sin(enemy1_angle[i]) * 0.45 * (-1.0);
					e_projectile_states[free] = 1;
				 */
				
				if(Time.getCurrentTime() > this.nextShot)
				{
					new Projectile("Projectile", new Vector(this.position.x, this.position.y), Math.cos(this.angle) * 0.45, Math.sin(this.angle) * 0.45 * (-1.0));
					this.nextShot = (long) (Time.getCurrentTime() +200 + Math.random() * 500);
				}
			}
			break;
		
		}
	}
	
	public void draw()
	{
		if(state == EXPLODING){
			
			double alpha = (Time.getCurrentTime() - explosion_start) / (explosion_end - explosion_start);
			GameLib.drawExplosion(this.position.x, this.position.y, alpha);
		}
		
		if(state == ACTIVE){
			GameLib.setColor(Color.CYAN);
			GameLib.drawCircle(this.position.x, this.position.y, this.getRadius());
		}
	}

}
