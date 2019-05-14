package lab6;

public class Threads {
	Container cont;
	
	Threads(Container c) {
		cont = c;
		
		MinFinderThread ob1 = new MinFinderThread("Minimum", cont);
		MaxFinderThread ob2 = new MaxFinderThread("Maximum", cont);
		SumFinderThread ob3 = new SumFinderThread("Sum", cont);
		
		try {
			ob1.t.join();
			ob2.t.join();
			ob3.t.join();
		} catch (InterruptedException e) {
			System.err.println("Threads interrupted");
		}
	}
	
	public class MinFinderThread implements Runnable {
		Thread t;
		String name;
		Container cont;
		
		MinFinderThread(String threadname, Container c) {
			name = threadname;
			cont = c;
			t = new Thread(this, name);
			t.start();
		}
		
		public void run() {
			System.out.println("Minimum: " + cont.minimum());
		}
	}
	
	public class MaxFinderThread implements Runnable {
		Thread t;
		String name;
		Container cont;
		
		MaxFinderThread(String threadname, Container c) {
			name = threadname;
			cont = c;
			t = new Thread(this, name);
			t.start();
		}
		
		public void run() {
			System.out.println("Maximum: " + cont.maximum());
		}
	}
	
	public class SumFinderThread implements Runnable {
		Thread t;
		String name;
		Container cont;
		
		SumFinderThread(String threadname, Container c) {
			name = threadname;
			cont = c;
			t = new Thread(this, name);
			t.start();
		}
		
		public void run() {
			System.out.println("Sum: " + cont.sum());
		}
	}
	
	public static class Timechecker implements Runnable {
		Thread t;
		long time;
		
		Timechecker(long time) {
			this.time = time;
			t = new Thread(this, "Timechecker");
			t.start();
		}
		
		public void run() {
			long endTime = System.currentTimeMillis() + time;
			
			while (System.currentTimeMillis() < endTime) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.err.println(t + " interrupted");
				}
			}
			
			System.out.println("Time is over");
			System.exit(0);
		}
	}
}