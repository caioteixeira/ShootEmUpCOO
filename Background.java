import java.awt.Color;

/*
 * Define o desenho do fundo
 */

public class Background extends GameEntity {
		Vector[] backgroundDistante;
		Vector[] backgroundProximo;
		double backDistanteCount = 0.0;
		double backDistanteSpeed = 0.070;
		double backProximoCount = 0.0;
		double backProximoSpeed = 0.045;
		
		Background(String id, Vector position)
		{
			super(id, position);
			backgroundProximo = new Vector[20];
			backgroundDistante = new Vector[50];
			
			
			for(int i = 0; i < backgroundDistante.length; i++)
			{
				backgroundDistante[i] = new Vector(Math.random() * GameLib.WIDTH, Math.random() * GameLib.HEIGHT);
			}
			
			for(int i = 0; i < backgroundProximo.length; i++)
			{
				backgroundProximo[i] = new Vector(Math.random() * GameLib.WIDTH, Math.random() * GameLib.HEIGHT);
			}
			
		}

		public void draw()
		{
			GameLib.setColor(Color.DARK_GRAY);
			backDistanteCount += backDistanteSpeed * Time.deltaTime();
			for(int i = 0; i < backgroundDistante.length; i++)
			{
				GameLib.fillRect(backgroundDistante[i].x, (backgroundDistante[i].y + backDistanteCount) % GameLib.HEIGHT, 2, 2);
			}
			GameLib.setColor(Color.GRAY);
			backProximoCount += backProximoSpeed * Time.deltaTime();
			
			for(int i = 0; i < backgroundProximo.length; i++){
				
				GameLib.fillRect(backgroundProximo[i].x, (backgroundProximo[i].y + backProximoCount) % GameLib.HEIGHT, 3, 3);
			}
		}
}
