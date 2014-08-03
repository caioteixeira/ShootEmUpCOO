
//Define Game Actors
//Deve ser superclasse de entidades que devem lidar com colis�es (Player, Inimigos, Projeteis)

public abstract class GameActor extends GameEntity {
	private int radius;
	
	
	GameActor(String id, Vector position)
	{
		this.position = position;
		this.id = id;
		GameController.Instance().Instantiate(this);
	}
	
	int getRadius()
	{
		return this.radius;
	}
	
	//Define o tratamento de colis�es
	void onCollision(GameActor collider)
	{
		
	}
}
