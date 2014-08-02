//Define vetores e opera��es uteis (dist�ncia, etc)


public class Vector {
	public int x;
	public int y;
	void Vector(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	//Retorna a dist�ncia entre dois pontos
	double distance(Vector vetor)
	{
		double dx = vetor.x - this.x;
		double dy = vetor.y - this.y;
		
		return Math.sqrt(dx*dx + dy*dy);
		
	}
}
