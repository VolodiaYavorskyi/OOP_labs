package lab3;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int command, index;
		String temp, p;
		File path;
		String[] children;
		Scheduler scheduler = new Scheduler();
		scheduler.setEvents(new ArrayList<Event>());
		
		System.out.println("Volodymyr Yavorskyi | CS-109 | Lab 3 | #14");
		System.out.println();
		System.out.println("Hello!");
		
		while (true) {
			System.out.println();
			System.out.println("1- add an event");
			System.out.println("2- show all events");
			System.out.println("3- show information about an event");
			System.out.println("4- change event's information");
			System.out.println("5- remove an event");
			System.out.println("6- remove all events");
			System.out.println("7- serialize to XML file");
			System.out.println("8- deserialize from XML file");
			System.out.println("9- exit");
			System.out.print("Command: ");
			
			command = scan.nextInt();
			scan.nextLine();
			
			switch (command) {
				case 1:
					scheduler.addEvent();
					break;
				case 2:
					scheduler.showEvents();
					break;
				case 3:
					scheduler.showEvents();
					System.out.print("Choose an event: ");
					index = scan.nextInt();
					scan.nextLine();
					index--;
					
					scheduler.getEvent(index).showInformation();
					break;
				case 4:
					scheduler.showEvents();
					System.out.print("Choose an event: ");
					index = scan.nextInt();
					scan.nextLine();
					index--;
					
					while (command != 0) {
						System.out.println();
						System.out.println("1- set the name");
						System.out.println("2- set the start time");
						System.out.println("3- set the duration");
						System.out.println("4- set the place");
						System.out.println("5- set the description");
						System.out.println("6- add participants");
						System.out.println("7- back");
						System.out.print("Command: ");
						
						command = scan.nextInt();
						scan.nextLine();
						
						switch (command) {
							case 1:
								System.out.print("Name: ");
								temp = scan.nextLine();
								scheduler.getEvent(index).setName(temp);
								break;
							case 2:
								System.out.print("Enter hour: ");
								temp = scan.nextLine();
								temp += ":";
								System.out.print("Enter minute: ");
								temp += scan.nextLine();
								scheduler.getEvent(index).setStartTime(temp);
								break;
							case 3:
								System.out.print("Hours: ");
								temp = scan.nextLine() + " hours ";
								System.out.print("Minutes: ");
								temp += scan.nextLine() + " minutes";
								scheduler.getEvent(index).setDuration(temp);
								break;
							case 4:
								System.out.print("Place: ");
								temp = scan.nextLine();
								scheduler.getEvent(index).setPlace(temp);
								break;
							case 5:
								System.out.print("Description: ");
								temp = scan.nextLine();
								scheduler.getEvent(index).setDescription(temp);
								break;
							case 6:
								System.out.println("Enter participants' names");
								System.out.println("Type 'stop' to end");
								
								do {
									temp = scan.nextLine();
									scheduler.getEvent(index).addParticipant(temp);
								} while (!temp.equals("stop"));
								
								scheduler.getEvent(index).removeLastParticipant();
								break;
							case 7:
								command = 0;
								break;
							default:
								System.out.println("Wrong command");
						}
					}
					break;
				case 5:
					scheduler.showEvents();
					System.out.print("Choose an event: ");
					index = scan.nextInt();
					scan.nextLine();
					index--;
					
					scheduler.removeEvent(index);
					break;
				case 6:
					scheduler.clear();
					break;
				case 7:
					System.out.println("Make a new directory?");
					System.out.println("1- yes");
					System.out.println("2- no");
					
					command = scan.nextInt();
					scan.nextLine();
					
					switch (command) {
						case 1:
							System.out.print("Directory name: ");
							temp = scan.nextLine();
							
							path = new File("C:\\Users\\1\\eclipse-workspace\\OOP_labs\\XML\\" + temp);
							
							if (!path.exists()) {
								path.mkdir();
							}
							
							p = "C:\\Users\\1\\eclipse-workspace\\OOP_labs\\XML\\" + temp +"\\";
							
							System.out.print("Filename: ");
							temp = scan.nextLine();
							scheduler.serialize(temp, p);
							break;
						case 2:
							p = "C:\\Users\\1\\eclipse-workspace\\OOP_labs\\XML\\";
							System.out.print("Filename: ");
							temp = scan.nextLine();
							scheduler.serialize(temp, p);
							break;
						default:
							System.out.println("Wrong command");
					}
					break;
				case 8:
					System.out.println("Default directory?");
					System.out.println("1- yes");
					System.out.println("2- no");
					
					command = scan.nextInt();
					scan.nextLine();
					
					switch (command) {
						case 1:
							p = "C:\\Users\\1\\eclipse-workspace\\OOP_labs\\XML\\";
							path = new File("C:\\Users\\1\\eclipse-workspace\\OOP_labs\\XML");
							
							children = path.list();
							System.out.println("Files:");
							
							for (String x: children) {
								System.out.println(x);
							}
							
							System.out.print("Filename: ");
							temp = scan.nextLine();
							scheduler.deserialize(temp, p);
							break;
						case 2:
							p = "C:\\Users\\1\\eclipse-workspace\\OOP_labs\\XML\\";
							path = new File("C:\\Users\\1\\eclipse-workspace\\OOP_labs\\XML");
							
							children = path.list();
							System.out.println("Files:");
							
							for (String x: children) {
								System.out.println(x);
							}
							
							System.out.print("Directory name: ");
							temp = scan.nextLine();
							p += temp + "\\";
							
							path = new File("C:\\Users\\1\\eclipse-workspace\\OOP_labs\\XML\\" + temp);
							
							children = path.list();
							System.out.println("Files:");
							
							for (String x: children) {
								System.out.println(x);
							}
							
							System.out.print("Filename: ");
							temp = scan.nextLine();
							scheduler.deserialize(temp, p);
							break;
						default:
							System.out.println("Wrong command");
					}
					break;
				case 9:
					scan.close();
					System.exit(0);
				default:
					System.out.println("Wrong command");
			}
		}
	}
}