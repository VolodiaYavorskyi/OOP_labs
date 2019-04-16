package lab1;

import java.util.Scanner;

public class Main {
	
	static void report(int command, StringBuffer sentence, StringBuffer string, char symb) {
		System.out.println();
		System.out.println("Debug information");
		System.out.println("Command: " + command);
		System.out.println("Input: " + sentence);
		System.out.println("String: " + string);
		System.out.println("Symbol: " + symb);
		System.out.println();
	} 
	
	public static void main(String[] args) {
		boolean debug = false;
		
		if (args != null) {
			boolean help = false;
			
			for (String x: args) {
				if (x.equals("-h") || x.equals("-help")) {
					help = true;
				}
				if (x.equals("-d") || x.equals("-debug")) {
					debug = true;
				}
			}
			
			if (help) {
				System.out.println("Volodymyr Yavorskyi | CS-109 | Lab 1 | #14");
				System.out.println();
				System.out.println("Task: Enter the text, insert a string "
						+ "after all words ending \nwith a certain symbol. "
						+ "Print the text before and after this operation.");
				System.out.println();
				System.out.println("Usage:");
				System.out.println("Type 1 to make the input");
				System.out.println("Type 2 to show the text you entered");
				System.out.println("Type 3 to calculate according to the task");
				System.out.println("Type 4 to show the result");
				System.out.println("Type 5 to exit the program");
				System.out.println();
				System.out.print("\"-help\" enabled, ");
				System.out.println("\"-debug\" " + (debug ? "enabled" : "disabled"));
			}
		}
		
		Scanner scan = new Scanner(System.in);
		
		int command;
		
		StringBuffer sentence = new StringBuffer(1000);
		StringBuffer string = new StringBuffer(100);
		
		char symb = '\0';
		
		while (true) {
			System.out.println();
			System.out.println("1- input");
			System.out.println("2- show the input");
			System.out.println("3- calculate");
			System.out.println("4- show the result");
			System.out.println("5- exit");
			System.out.print("Command: ");
			
			command = scan.nextInt();
			scan.nextLine();
			
			switch (command) {
				case 1:
					if (debug) {
						report(command, sentence, string, symb);
					}
					
					System.out.println();
					
					System.out.print("Input your text: ");
					String input = scan.nextLine();
					sentence.append(input);
					
					System.out.print("Now input a string you want to insert: ");
					input = scan.nextLine();
					string.append(input);
					
					System.out.print("And the ending symbol: ");
					symb = scan.next().charAt(0);
					break;
				case 2:
					if (debug) {
						report(command, sentence, string, symb);
					}
					
					System.out.println();
					if (sentence.length() != 0) {
						System.out.println(sentence);
					}
					else {
						System.out.println("No sentence");
					}
					break;
				case 3:
					if (debug) {
						report(command, sentence, string, symb);
					}
					
					int n = sentence.length();
					
					for (int i = 0; i < n - 1; i++) { 
						if (sentence.charAt(i) == symb && (sentence.charAt(i + 1) == ' ' 
								|| sentence.charAt(i + 1) == '.')) {
							sentence.insert(i + 1, ' ').insert(i + 2, string);
							n += string.length() + 1;
							i += string.length() + 1;
						}
					}
					if (sentence.charAt(n - 1) == symb) {
						sentence.append(' ').append(string);
					}
					
					if (debug) {
						report(command, sentence, string, symb);
					}
					break;
				case 4:
					if (debug) {
						report(command, sentence, string, symb);
					}
					
					if (sentence.length() != 0) {
						System.out.println(sentence);
					}
					else {
						System.out.println("No sentence");
					}
					break;
				case 5:
					scan.close();
					System.out.println();
					System.out.println("Exiting program");
					System.exit(0);
				default:
					System.out.println("Wrong command");
			}
		}
	}
}