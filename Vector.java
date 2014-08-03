//Define vetores e operações uteis (distância, etc)


public class Vector {
	public double x;
	public double y;
	Vector(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	//Retorna a distância entre dois pontos
	double distance(Vector vetor)
	{
		double dx = vetor.x - this.x;
		double dy = vetor.y - this.y;
		
		return Math.sqrt(dx*dx + dy*dy);
		
	}
}
