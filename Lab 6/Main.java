package lab6;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		long maxTime, startTime;
		System.out.print("Maximum time in seconds: ");
		maxTime = scan.nextInt() * 1000;
		scan.close();
		
		new Threads.Timechecker(maxTime);
		
		Container cont = new Container(3000);
		
		cont.fill();
		
		startTime = System.currentTimeMillis();
		
		System.out.println();
		System.out.println("//////////////////////////////");
		System.out.println("////      Consistent      ////");
		System.out.println("//////////////////////////////");
		System.out.println("Minimum: " + cont.minimum());
		System.out.println("Maximum: " + cont.maximum());
		System.out.println("Sum: " + cont.sum());
		System.out.println("______________________________");
		System.out.println("Total time: " + (System.currentTimeMillis() - startTime) / 1000 +" seconds");
		System.out.println("______________________________");
		System.out.println("//////////////////////////////");
		System.out.println();
		
		startTime = System.currentTimeMillis();
		
		System.out.println();
		System.out.println("//////////////////////////////");
		System.out.println("////      Concurrent      ////");
		System.out.println("//////////////////////////////");
		new Threads(cont);
		System.out.println("______________________________");
		System.out.println("Total time: " + (System.currentTimeMillis() - startTime) / 1000 +" seconds");
		System.out.println("______________________________");
		System.out.println("//////////////////////////////");
		System.out.println();
	}
}