package lab2;

import java.util.Scanner;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int command, cn;
		int size = 0;
		int number = 0;
		String temp = null;
		Container[] containers = null;
		
		System.out.println("Volodymyr Yavorskyi | CS-109 | Lab 2 | #14");
		System.out.println();
		System.out.println("Hello!");
		
		while (true) {
			System.out.println();
			System.out.println("1- make containers");
			System.out.println("2- show operations");
			System.out.println("3- show all containers");
			System.out.println("4- delete containers");
			System.out.println("5- exit");
			System.out.print("Command: ");
			
			command = scan.nextInt();
			scan.nextLine();
			
			switch (command) {
				case 1:
					System.out.print("How much containers do you want to make: ");
					number = scan.nextInt();
					scan.nextLine();
					
					try {
						containers = new Container[number];
						System.out.println("You created " + number + " containers");
					} catch (NegativeArraySizeException e) {
						System.out.println("Enter positive number");
					}
					
					System.out.print("Enter their size: ");
					size = scan.nextInt();
					scan.nextLine();
					
					try {
						for (int i = 0; i < number; i++) {
							containers[i] = new Container(size);
						}
					} catch (NegativeArraySizeException e) {
						System.out.println("Enter positive number");
					}
					break;
				case 2:
					System.out.println("Choose container you want to work with");
					System.out.print("Possible: ");
					
					for (int i = 0; i < number; i++) {
						System.out.print(i + 1 + " ");
					}
					System.out.println();
					
					cn = scan.nextInt();
					scan.nextLine();
					cn--;
					
					if (cn < 0 || cn > number - 1) {
						System.out.println("Wrong number");
						break;
					}
					
					System.out.println("1- show container's contents");
					System.out.println("2- add element");
					System.out.println("3- clear all elements");
					System.out.println("4- remove a string");
					System.out.println("5- show size");
					System.out.println("6- check if container has certain element");
					System.out.println("7- check if container has another container's elements");
					System.out.println("8- iterate (while)");
					System.out.println("9- iterate (for each)");
					System.out.println("10- serialize");
					System.out.println("11- deserialize");
					System.out.println("12- sort by alphabet");
					System.out.println("13- show an element");
					System.out.println("14- set an element");
					System.out.print("Command: ");
					
					command = scan.nextInt();
					scan.nextLine();
					
					switch (command) {
						case 1:
							try {
								System.out.println(containers[cn].toString());
							} catch (NullPointerException e) {
								System.out.println("No elements");
							}
							break;
						case 2:
							System.out.print("String you want to add: ");
							temp = scan.nextLine();
							containers[cn].add(temp);
							break;
						case 3:
							containers[cn].clear();
							break;
						case 4:
							System.out.print("String you want to remove: ");
							temp = scan.nextLine();
							containers[cn].remove(temp);
							break;
						case 5:
							System.out.println("Size: " + containers[cn].size());
							break;
						case 6:
							System.out.print("Element: ");
							temp = scan.nextLine();
							System.out.println(containers[cn].contains(temp));
							break;
						case 7:
							System.out.print("Container number: ");
							command = scan.nextInt();
							scan.nextLine();
							command--;
							System.out.println(containers[cn].containsAll(containers[command]));
							break;
						case 8:
							Iterator<String> iter = containers[cn].iterator();
							while (iter.hasNext()) {
								System.out.println(iter.next());
							}
							break;
						case 9:
							for (String x: containers[cn]) {
								System.out.println(x);
							}
							break;
						case 10:
							System.out.println("File name: ");
							temp = scan.nextLine();
							containers[cn].serialize(temp);
							break;
						case 11:
							System.out.println("File name: ");
							temp = scan.nextLine();
							containers[cn].deserialize(temp);
							break;
						case 12:
							for (int i = 0; i < size; i++) {
								for (int j = 0 ; j < i - 1; j++) {
									if (containers[cn].get(j) != null && containers[cn].get(j + 1) != null) {
										if (containers[cn].get(j).charAt(0) 
												> containers[cn].get(j + 1).charAt(0)) {
											temp = containers[cn].get(j);
											containers[cn].set(j, containers[cn].get(j + 1));
											containers[cn].set(j + 1, temp);
										}
									}
								}
							}
						case 13:
							System.out.println("Element's index: ");
							command = scan.nextInt();
							scan.nextLine();
							command--;
							System.out.println(containers[cn].get(command));
							break;
						case 14:
							System.out.println("Index: ");
							command = scan.nextInt();
							scan.nextLine();
							command--;
							System.out.println("String: ");
							temp = scan.nextLine();
							containers[cn].set(command, temp);
							break;
						default:
							System.out.println("Wrong command");
					}
					break;
				case 3:
					try {
						for (Container x: containers) {
							System.out.println(x.toString());
						}
					} catch (NullPointerException e) {
						System.out.println("Initialize containers first");
					}
					break;
				case 4:
					containers = null;
					System.out.println("Success");
					break;
				case 5:
					scan.close();
					System.exit(0);
				default:
					System.out.println("Wrong command");
			}
		}
	}
}