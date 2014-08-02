//Define uma entidade de jogo


public abstract class GameEntity {
	
	protected String id;
	public Vector position;
	
	public String getId() {
		return id;
	}
	//Garante que entidades não sejam inicializadas inadequadamente
	protected GameEntity(){}

	void GameEntity(String id, Vector position)
	{
		this.id = id;
		this.position = position;
	}
	
	public void update()
	{
		
	}
	
	public void draw()
	{
		
	}

}
