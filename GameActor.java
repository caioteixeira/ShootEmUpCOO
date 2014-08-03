
//Define Game Actors
//Game Actor são entidades que podem sofrer colisões
//É filha de GameEntity, mas implementa o método onCollision que é chamado ao ser detectada colisão com outro GameActor

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
