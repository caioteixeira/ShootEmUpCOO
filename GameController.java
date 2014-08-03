import java.awt.Color;
import java.util.LinkedList;

//Classe responsável por controlar todo o gameplay, deve ser Singleton
public class GameController {
	
	LinkedList<GameEntity> entities;
	LinkedList<GameActor> gameActors;
	
	long nextEnemy1;
	
	
	private GameController()
	{
		entities = new LinkedList<GameEntity>();
		gameActors = new LinkedList<GameActor>();
		tempEntities = new LinkedList<GameEntity>();
		tempActors = new LinkedList<GameActor>();
		tempDEntities = new LinkedList<GameEntity>();
		tempDActors = new LinkedList<GameActor>();
		
		
		nextEnemy1 = Time.getCurrentTime() + 2000;
	};
	private static GameController instance;
	public static GameController Instance()
	{
		if(GameController.instance == null)
			GameController.instance = new GameController();
		return GameController.instance;
	}
	
	/*
	 * Trecho responsável por controlar a criação de novas entidades
	 * Objetos a serem adicionados devem ser adicionados somente no próximo frame para evitar quebra nos loops principais
	 */
	private LinkedList<GameEntity> tempEntities;
	private LinkedList<GameActor> tempActors;
	
	//Adiciona objetos nas listas
	public void Instantiate(GameEntity entity)
	{
		if(tempEntities == null)
			tempEntities = new LinkedList<GameEntity>();
		tempEntities.add(entity);
	}
	public void Instantiate(GameActor actor)
	{
		if(tempActors == null)
			tempActors = new LinkedList<GameActor>();
		tempActors.add(actor);
		Instantiate((GameEntity)actor);
	}
	
	/*
	 * Trecho responsável por destruir entidades
	 */
	private LinkedList<GameEntity> tempDEntities;
	private LinkedList<GameActor> tempDActors;
	public void Destroy(GameEntity entity)
	{
		if(tempDEntities == null)
			tempDEntities = new LinkedList<GameEntity>();
		tempDEntities.add(entity);
	}
	public void Destroy(GameActor actor)
	{
		if(tempDActors == null)
			tempDActors = new LinkedList<GameActor>();
		tempDActors.add(actor);
		Destroy((GameEntity) actor);
	}
	
	
	//Atualiza listas de entidades
	private void updateLists()
	{
		//Loops de instancialização de novas entidades
		for(GameEntity e: tempEntities)
			entities.add(e);
		tempEntities.clear();
		for(GameActor e: tempActors)
			gameActors.add(e);
		tempActors.clear();
		//Loops de destruição de objetos
		for(GameEntity e: tempDEntities)
			entities.remove(e);
		tempDEntities.clear();
		for(GameActor a: tempDActors)
			gameActors.remove(a);
		tempDActors.clear();
		
	}
	
	//Atualiza inimigos
	private void updateEnemies()
	{
		//Precisa lancar novo inimigo 1?
		//System.out.println("Cur:" + Time.getCurrentTime() + "\nNext:"+this.nextEnemy1);
		if(Time.getCurrentTime() > this.nextEnemy1)
		{
			//System.out.println("Instanciou");
			Enemy1 novo = new Enemy1("Enemy1", new Vector(Math.random() * (GameLib.WIDTH -20.0) + 10.0, -10.0));
			novo.velocity = 0.20 + Math.random() * 0.15;
			novo.angle = 3 * Math.PI / 2;
			novo.rotationvelocity = 0.0;
			this.nextEnemy1 = Time.getCurrentTime() + 500;
		}
	}
	
	public void update()
	{
		updateLists();
		
		//Loop de física
		for(GameActor e : gameActors)
		{
			for(GameActor a: gameActors)
			{
				/*
				 * double dx = e_projectile_X[i] - player_X;
					double dy = e_projectile_Y[i] - player_Y;
					double dist = Math.sqrt(dx * dx + dy * dy);
					
					if(dist < (player_radius + e_projectile_radius)
				 */
				
				double dx = e.position.x - a.position.x;
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
		
		updateEnemies();
		
		//Loop de desenho
		for(GameEntity e : entities)
		{
			e.draw();
		}
		
	}
	
}
