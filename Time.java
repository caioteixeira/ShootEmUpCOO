
public class Time {
	
	
	private static long currentTime;
	private static long delta;
	public static void setCurrentTime()
	{
		currentTime = System.currentTimeMillis();
	}
	
	public static void setDeltaTime()
	{
		delta = System.currentTimeMillis() - currentTime;
	}
	
	public static long getCurrentTime()
	{
		return currentTime;
	}
	
	public static long deltaTime()
	{
		return delta;
	}
}
