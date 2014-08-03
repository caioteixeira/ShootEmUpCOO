import java.awt.Color;
import java.util.LinkedList;

//Classe respons�vel por controlar todo o gameplay, deve ser Singleton

/*
 * Esta classe implementa dois padr�es de projeto:
 * 
 * -Update Method: Pode ser interpretado com uma varia��o do padr�o Observer, como descrito na seguinte refer�ncia:
 *  http://gameprogrammingpatterns.com/update-method.html
 * 
 * -Singleton: Deve ser um Singleton, pois esta classe tamb�m controla quais objetos devem ser "observers" ou que deixam de ser.
 * 
 */
public class GameController {
	
	LinkedList<GameEntity> entities;
	LinkedList<GameActor> gameActors;
	
	long nextEnemy1;
	
	double enemy2_spawnX = GameLib.WIDTH * 0.20;			// coordenada x do próximo inimigo tipo 2 a aparecer
	int enemy2_count = 0;									// contagem de inimigos tipo 2 (usada na "formação de voo")
	long nextEnemy2;
	
	private GameController()
	{
		entities = new LinkedList<GameEntity>();
		gameActors = new LinkedList<GameActor>();
		tempEntities = new LinkedList<GameEntity>();
		tempActors = new LinkedList<GameActor>();
		tempDEntities = new LinkedList<GameEntity>();
		tempDActors = new LinkedList<GameActor>();
		
		
		nextEnemy1 = Time.getCurrentTime() + 2000;
		nextEnemy2 = Time.getCurrentTime() + 7000;
	};
	private static GameController instance;
	public static GameController Instance()
	{
		if(GameController.instance == null)
			GameController.instance = new GameController();
		return GameController.instance;
	}
	
	/*
	 * Trecho respons�vel por controlar a cria��o de novas entidades
	 * Objetos a serem adicionados devem ser adicionados somente no pr�ximo frame para evitar quebra nos loops principais
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
	 * Trecho respons�vel por destruir entidades
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
		//Loops de instancializa��o de novas entidades
		for(GameEntity e: tempEntities)
			entities.add(e);
		tempEntities.clear();
		for(GameActor e: tempActors)
			gameActors.add(e);
		tempActors.clear();
		//Loops de destrui��o de objetos
		for(GameEntity e: tempDEntities)
			entities.remove(e);
		tempDEntities.clear();
		for(GameActor a: tempDActors)
			gameActors.remove(a);
		tempDActors.clear();
		
	}
	
	//Atualiza inimigos
	//Verifica quando novos inimigos devem ser lan�ados
	private void updateEnemies()
	{
		//Precisa lancar novo inimigo 1?
		if(Time.getCurrentTime() > this.nextEnemy1)
		{
			//System.out.println("Instanciou");
			Enemy1 novo = new Enemy1("Enemy1", new Vector(Math.random() * (GameLib.WIDTH -20.0) + 10.0, -10.0));
			novo.velocity = 0.20 + Math.random() * 0.15;
			novo.angle = 3 * Math.PI / 2;
			novo.rotationvelocity = 0.0;
			this.nextEnemy1 = Time.getCurrentTime() + 500;
		}
		
		//Precisa lancar novo inimigo 2:
		if(Time.getCurrentTime() > nextEnemy2){
			
			//int free = findFreeIndex(enemy2_states);
							
			//if(free < enemy2_states.length){
			
			Vector position = new Vector (enemy2_spawnX, -10.0);
			Enemy2 novo = new Enemy2("Enemy2", position);
			novo.velocity = 0.42;
			novo.angle = (3 * Math.PI) / 2;
			novo.rotationvelocity = 0.0;

			enemy2_count++;
			
			if(enemy2_count < 10){
				
				nextEnemy2 = Time.getCurrentTime() + 120;
			}
			else {
				
				enemy2_count = 0;
				enemy2_spawnX = Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8;
				nextEnemy2 = (long) (Time.getCurrentTime() + 3000 + Math.random() * 3000);
			}
		}
	}
	
	
	//Implementa��o do padr�o Update Method
	/*
	 * O ciclo de vida do M�todo update � dividido nas seguintes etapas:
	 * 1)Atualizar listas: Verifica se novas entidades foram criadas ou destruidas
	 * 2)Loop de f�sica: Verifica se existem colis�es entre duas entidades, se houver chama seus respectivos m�todos de tratamento de colis�o
	 * 3)Loop Update: chama o m�todo update() de todos as entidades
	 * 4)Loop Draw: chama o m�todo draw() de todas as entidades
	 */
	public void update()
	{
		updateLists();
		
		//Loop de f�sica
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
					//Ocorre colis�o com a
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
