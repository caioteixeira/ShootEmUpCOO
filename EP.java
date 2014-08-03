public class EP {
	
	
	public static void busyWait(long time){
		
		//System.out.println(time);
		while(System.currentTimeMillis() < time) Thread.yield();
	}
	
	/* Encontra e devolve o primeiro índice do  */
	/* array referente a uma posição "inativa". */
	
	public static void main(String[] args)
	{
		boolean running = true;
		Time.setCurrentTime();
						
		/* iniciado interface gráfica */
		
		
		GameLib.initGraphics();
		
		
		/*************************************************************************************************/
		/*                                                                                               */
		/* Main loop do jogo                                                                             */
		/*                                                                                               */
		/* O main loop do jogo possui executa as seguintes operações:                                    */
		/*                                                                                               */
		/* 1) Verifica se há colisões e atualiza estados dos elementos conforme a necessidade.           */
		/*                                                                                               */
		/* 2) Atualiza estados dos elementos baseados no tempo que correu desde a última atualização     */
		/*    e no timestamp atual: posição e orientação, execução de disparos de projéteis, etc.        */
		/*                                                                                               */
		/* 3) Processa entrada do usuário (teclado) e atualiza estados do player conforme a necessidade. */
		/*                                                                                               */
		/* 4) Desenha a cena, a partir dos estados dos elementos.                                        */
		/*                                                                                               */
		/* 5) Espera um período de tempo (de modo que delta seja aproximadamente sempre constante).      */
		/*                                                                                               */
		/*************************************************************************************************/
		
		
		/*
		 * Define GameController, classe responsavel por gerenciar a atualizacao das entidades de jogo
		 */
		GameController controller = GameController.Instance();
		Background bg = new Background("background", new Vector(0,0));
		Player player = Player.Instance();
		
		while(running){
		
			/* Usada para atualizar o estado dos elementos do jogo    */
			/* (player, projéteis e inimigos) "delta" indica quantos  */
			/* ms se passaram desde a última atualização.             */
			
			Time.setDeltaTime();
			
			Time.setCurrentTime();
			
			controller.update();
			
			/* chamama a display() da classe GameLib atualiza o desenho exibido pela interface do jogo. */
			
			if(GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) running = false;
			
			GameLib.display();
			
			/* faz uma pausa de modo que cada execução do laço do main loop demore aproximadamente 5 ms. */
			
			//System.out.println(Time.deltaTime());
			busyWait(Time.getCurrentTime() + 5);
		}
		
		System.exit(0);
	}
}

