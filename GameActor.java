
//Define Game Actors
//Deve ser superclasse de entidades que devem lidar com colisões (Player, Inimigos, Projeteis)

public abstract class GameActor extends GameEntity {
	protected double radius;
	
	
	GameActor(String id, Vector position)
	{
		this.position = position;
		this.id = id;
		GameController.Instance().Instantiate(this);
	}
	
	double getRadius()
	{
		return this.radius;
	}
	
	//Define o tratamento de colisões
	void onCollision(GameActor collider)
	{
		
	}
}
