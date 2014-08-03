import java.awt.Color;

/*
 * Define o comportamento de projéteis
 */

public class Projectile extends GameActor {
	
	//Definem velocidade do projetil
	double vx;
	double vy;
	

	Projectile(String id, Vector position, double vx, double vy) {
		super(id, position);
		this.vx = vx;
		this.vy = vy;
		this.radius = 2.0;
	}
	
	void onCollision(GameActor collider)
	{
		switch(collider.id)
		{
		case "Player":
			if(this.id == "PlayerProjectile")
				break;
		case "Enemy1":
			if(this.id == "Projectile")
				break;
		case "Enemy2":
			if(this.id == "Projectile")
				break;
		case "Enemy3":
			if(this.id == "Projectile")
				break;
			GameController.Instance().Destroy(this);
			break;
		}
	}
	
	public void update()
	{
		//Verifica se o projetil está na tela
		if(this.position.y < 0 || this.position.y > GameLib.HEIGHT)
		{
			GameController.Instance().Destroy(this);
		}
		else
		{
			this.position.x += this.vx * Time.deltaTime();
			this.position.y += this.vy * Time.deltaTime();
		}
	}
	
	public void draw()
	{
		GameLib.setColor(Color.RED);
		GameLib.drawCircle(this.position.x, this.position.y, this.getRadius());
	}

}
