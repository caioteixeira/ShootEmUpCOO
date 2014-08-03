//Define uma entidade de jogo
/*
 * Baseado no padr�o UpdateMethod()
 * Esta classe abstrata define quais metodos uma entidade de jogo deve possuir
 */

public abstract class GameEntity {
	
	protected String id;
	public Vector position;
	
	public String getId() {
		return id;
	}
	//Garante que entidades n�o sejam inicializadas inadequadamente
	protected GameEntity(){}

	GameEntity(String id, Vector position)
	{
		this.id = id;
		this.position = position;
		
		//Ao ser instanciada, � automaticamente incluinda na lista do GameController
		GameController.Instance().Instantiate(this);
	}
	
	//Responsavel por atualizar o estado da entidade
	public void update()
	{
		
	}
	
	//Responsavel por desenhar a entidade na tela
	public void draw()
	{
		
	}

}
