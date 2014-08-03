
public class Time {
	
	
	private static double currentTime;
	public static void setCurrentTime()
	{
		currentTime = System.currentTimeMillis();
	}
	
	public static double getCurrentTime()
	{
		return currentTime;
	}
	
	public static double deltaTime()
	{
		return System.currentTimeMillis() - getCurrentTime();
	}
}
