
public class Enemy1 extends Enemy {

	Enemy1(String id, Vector position, int radius) {
		super(id, position, radius);
	}
	
	
	public void onCollision(GameActor collider)
	{
		switch(collider.getId())
		{
		case "Projectile":
			if(this.state == ACTIVE)
			{
				this.explosion_start = Time.getCurrentTime();
				this.explosion_end = Time.getCurrentTime() + 500;
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
				
				this.state = INACTIVE;
			}
			else {
				
				//Atualiza posição
				this.position.x += this.velocity * Math.cos(this.angle) * Time.deltaTime();
				this.position.y += this.velocity * Math.sin(this.angle) * Time.deltaTime();
				this.angle += this.rotationvelocity * Time.deltaTime();
			
				
				/*
				if(currentTime > enemy1_nextShoot[i] && enemy1_Y[i] < player_Y){
																					
					int free = findFreeIndex(e_projectile_states);
					
					if(free < e_projectile_states.length){
						
						e_projectile_X[free] = enemy1_X[i];
						e_projectile_Y[free] = enemy1_Y[i];
						e_projectile_VX[free] = Math.cos(enemy1_angle[i]) * 0.45;
						e_projectile_VY[free] = Math.sin(enemy1_angle[i]) * 0.45 * (-1.0);
						e_projectile_states[free] = 1;
						
						enemy1_nextShoot[i] = (long) (currentTime + 200 + Math.random() * 500);
					}
				}*/
			}
			break;
		
		}
	}
	
	public void draw()
	{
		
	}

}
