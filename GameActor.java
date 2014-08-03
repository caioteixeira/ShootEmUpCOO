
//Define Game Actors
//Game Actor s�o entidades que podem sofrer colis�es
//� filha de GameEntity, mas implementa o m�todo onCollision que � chamado ao ser detectada colis�o com outro GameActor

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
	
	//Define o tratamento de colis�es
	void onCollision(GameActor collider)
	{
		
	}
}
