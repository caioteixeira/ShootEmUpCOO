
//Define Game Actors
//Deve ser superclasse de entidades que devem lidar com colisões (Player, Inimigos, Projeteis)

public abstract class GameActor extends GameEntity {
	private int radius;
	
	
	GameActor(String id, Vector position, int radius)
	{
		this.radius = radius;
		this.position = position;
		this.id = id;
	}
	
	int getRadius()
	{
		return this.radius;
	}
	
	//Define o tratamento de colisões
	void onCollision(GameActor collider)
	{
		
	}
}
