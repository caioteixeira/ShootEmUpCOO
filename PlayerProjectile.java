import java.awt.Color;


public class PlayerProjectile extends Projectile {

	PlayerProjectile(String id, Vector position, double vx, double vy) {
		super(id, position, vx, vy);
		// TODO Auto-generated constructor stub
	}
	public void draw()
	{
		GameLib.setColor(Color.GREEN);
		GameLib.drawLine(this.position.x, this.position.y - 5, this.position.x, this.position.y + 5);
		GameLib.drawLine(this.position.x- 1, this.position.y- 3, this.position.x - 1, this.position.y + 3);
		GameLib.drawLine(this.position.x + 1, this.position.y - 3,this.position.x + 1, this.position.y + 3);
	}
}
