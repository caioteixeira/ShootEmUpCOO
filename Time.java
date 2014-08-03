
public class Time {
	
	
	private static long currentTime;
	public static void setCurrentTime()
	{
		currentTime = System.currentTimeMillis();
	}
	
	public static long getCurrentTime()
	{
		return currentTime;
	}
	
	public static long deltaTime()
	{
		return System.currentTimeMillis() - getCurrentTime();
	}
}
