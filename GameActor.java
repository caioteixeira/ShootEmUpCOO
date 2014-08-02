
//Define Game Actors
//Deve ser superclasse de entidades que devem lidar com colisões (Player, Inimigos, Projeteis)

public abstract class GameActor extends GameEntity {
	private int radius;
	
	
	void GameActor(String id, Vector position, int radius)
	{
		this.radius = radius;
	}
	
	int getRadius()
	{
		return this.radius;
	}
}
