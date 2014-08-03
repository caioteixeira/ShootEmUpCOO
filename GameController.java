import java.util.LinkedList;

//Classe responsável por controlar todo o gameplay, deve ser Singleton
public class GameController {
	
	LinkedList<GameEntity> entities;
	LinkedList<GameActor> gameActors;
	
	private GameController()
	{
		entities = new LinkedList<GameEntity>();
		gameActors = new LinkedList<GameActor>();
	};
	private static GameController instance;
	public GameController Instance()
	{
		if(this.instance == null)
			this.instance = new GameController();
		return this.instance;
	}
	
	//Adiciona objetos nas listas
	public void Instantiate(GameEntity entity)
	{
		entities.add(entity);
	}
	public void Instantiate(GameActor actor)
	{
		gameActors.add(actor);
		entities.add(actor);
	}
	
	
	public void update()
	{
		//Loop de física
		for(GameActor e : gameActors)
		{
			for(GameActor a: gameActors)
			{
				double dx = e.position.x - a.position.y;
				double dy = e.position.y - a.position.y;
				double dist = Math.sqrt(dx * dx + dy * dy);
				if(dist < e.getRadius() + a.getRadius())
				{
					//Ocorre colisão com a
					e.onCollision(a);
				}
			}
		}
		
		
		//Loop de update
		for(GameEntity e : entities)
		{
			e.update();
		}
		
		//Loop de desenho
		for(GameEntity e : entities)
		{
			e.draw();
		}
	}
	
}
