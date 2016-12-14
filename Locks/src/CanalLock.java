
public class CanalLock {
	private boolean upstreamGateOpen = true;
	private boolean downstreamGateOpen = false;
	private boolean waterLevelHigh = true;
	private int numBoats = 0;
	
	public synchronized void raise()
	{
		while(numBoats != 0){}
		System.out.println(Thread.currentThread().getName() + ": Raising water.");
		try {
			downstreamGateOpen = false;
			Thread.sleep(10000);
			waterLevelHigh = true;
			upstreamGateOpen = true; 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ": Water raised.");
		notifyAll();
	}
	
	public synchronized void lower()
	{
		while(numBoats < 4){}
		System.out.println(Thread.currentThread().getName() + ": Lowering water.");
		try {
			upstreamGateOpen = false;
			Thread.sleep(10000);
			waterLevelHigh = false;
			downstreamGateOpen = true; 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ": Water lowered.");
		notifyAll();
	}
	
	public synchronized void pass()
	{
		System.out.println(Thread.currentThread().getName() + ": Boat trying to enter lock.");
		while(upstreamGateOpen)
		{
			System.out.println(Thread.currentThread().getName() + ": Boat entering lock.");
			try {
				Thread.sleep(10); //Boat enters lock
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			numBoats++;
			System.out.println(Thread.currentThread().getName() + ": Boat has entered lock, there are currently " + numBoats + " boats in the lock.");
			try {
				wait();
			} catch (InterruptedException e) {}
			
		}
//		while(!upstreamGateOpen || numBoats >= 4)
//		{
//			try {
//				wait();
//			} catch (InterruptedException e) {}
//		}
//		while(waterLevelHigh)
//		{
//			try {
//				System.out.println(Thread.currentThread().getName() + ": Boat entering lock.");
//				Thread.sleep(5000);	//Boat enters lock
//				numBoats++;
//				System.out.println(Thread.currentThread().getName() + ": Boat has entered lock, there are currently " + numBoats + " boats in the lock.");
//				notifyAll();
//				wait();
//			} catch (InterruptedException e) {}
//		}
//		while(!downstreamGateOpen){}
//		try {
//			System.out.println(Thread.currentThread().getName() + ": Boat leaving lock.");
//			Thread.sleep(5000);
//			numBoats--;
//			System.out.println(Thread.currentThread().getName() + ": Boat has left lock.");
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
}
