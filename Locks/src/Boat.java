
public class Boat extends Thread{
	private CanalLock c;
	
	public Boat(CanalLock c, String s)
	{
		this.c = c;
		this.setName(s);
	}
	
	@Override
	public void run()
	{
		c.pass();
	} 
}
