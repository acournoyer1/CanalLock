
public class LockController extends Thread{
	private CanalLock c;
	
	public LockController(CanalLock c, String s)
	{
		this.c = c;
		this.setName(s);
	}
	
	@Override
	public void run()
	{
		c.lower();
		c.raise();
	}
	
	public static void main(String args[])
	{
		CanalLock c = new CanalLock();
		Boat b1 = new Boat(c, "Boat 1");
		Boat b2 = new Boat(c, "Boat 2");
		Boat b3 = new Boat(c, "Boat 3");
		Boat b4 = new Boat(c, "Boat 4");
		LockController control = new LockController(c, "Controller");
		
		b1.start();
		b2.start();
		b3.start();
		b4.start();
		control.start();
	}
}
