package lab7;

import java.io.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.util.Scanner;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		boolean automode = false;
		
		for (String x: args) {
			if (x.equals("-auto")) {
				automode = true;
			}
		}
		
		Scanner scan;
		int command, index;
		String temp;
		File path;
		String[] children;
		Scheduler scheduler = new Scheduler();
		scheduler.setEvents(new LinkedList<Event>());
		
		System.out.println("Volodymyr Yavorskyi | CS-109 | Lab 3 | #14");
		System.out.println();
		System.out.println("Hello!");
		
		if (automode) {
			System.out.println("Automode enabled");
			System.out.println();
		}
		
		if (automode) {
			try {
				scan = new Scanner(new File("automode.txt"));
			} catch (FileNotFoundException e) {
				System.err.println("File not found");
				scan = new Scanner(System.in);
			}
		} else {
			scan = new Scanner(System.in);
		}
		
		while (true) {
			System.out.println();
			System.out.println("1- add an event");
			System.out.println("2- show all events");
			System.out.println("3- show information about an event");
			System.out.println("4- change event's information");
			System.out.println("5- remove an event");
			System.out.println("6- remove all events");
			System.out.println("7- serialize");
			System.out.println("8- deserialize");
			System.out.println("9- exit");
			System.out.print("Command: ");
			
			command = scan.nextInt();
			scan.nextLine();
			
			switch (command) {
				case 1:
					scheduler.addEvent(new Event());
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
								
								if (!InputChecker.correctStartTime(temp)) {
									System.out.println("Wrong input");
									temp = null;
								}
								
								scheduler.getEvent(index).setStartTime(temp);
								break;
							case 3:
								System.out.print("Hours: ");
								temp = scan.nextLine() + " hours ";
								System.out.print("Minutes: ");
								temp += scan.nextLine() + " minutes";
								
								if (!InputChecker.correctDuration(temp)) {
									System.out.println("Wrong input");
									temp = null;
								}
								
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
									
									if (!InputChecker.correctParticipant(temp)) {
										System.out.println("Wrong input");
										temp = "*fail*";
									}
									
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
					System.out.println("1- Standard serialization");
					System.out.println("2- XML serialization");
					
					command = scan.nextInt();
					scan.nextLine();
					
					switch (command) {
						case 1:
							System.out.print("Filename: ");
							temp = scan.nextLine();
							serializeSTD(scheduler, temp);
							break;
						case 2:
							System.out.print("Filename: ");
							temp = scan.nextLine();
							serializeXML(scheduler, temp);
							break;
						default:
							System.out.println("Wrong command");
					}
					break;
				case 8:
					System.out.println("1- From standard file");
					System.out.println("2- From XML file");
					
					command = scan.nextInt();
					scan.nextLine();
					
					switch (command) {
						case 1:
							path = new File("C:\\Users\\1\\eclipse-workspace\\OOP_labs\\STD");
							
							children = path.list();
							System.out.println("Files:");
							
							for (String x: children) {
								System.out.println(x);
							}
							
							System.out.print("Filename: ");
							temp = scan.nextLine();
							
							deserializeSTD(scheduler, temp);
							break;
						case 2:
							path = new File("C:\\Users\\1\\eclipse-workspace\\OOP_labs\\XML");
							
							children = path.list();
							System.out.println("Files:");
							
							for (String x: children) {
								System.out.println(x);
							}
							
							System.out.print("Filename: ");
							temp = scan.nextLine();
							
							deserializeXML(scheduler, temp);
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
	
	public static void serializeSTD(Scheduler sch, String filename) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		String p = "C:\\Users\\1\\eclipse-workspace\\OOP_labs\\STD\\";
		
		try {
			fos = new FileOutputStream(p + filename);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject((Integer) sch.getEvents().size());
			
			for (Event x: sch.getEvents()) {
				oos.writeObject(x);
			}
			
			oos.close();
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}
	
	public static void deserializeSTD(Scheduler sch, String filename) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		String p = "C:\\Users\\1\\eclipse-workspace\\OOP_labs\\STD\\";
		
		try {
			fis = new FileInputStream(p + filename);
			ois = new ObjectInputStream(fis);
			
			Integer size = (Integer) ois.readObject();
			
			for (int i = 0; i < size; i++) {
				sch.addEvent((Event) ois.readObject());
			}
			
			ois.close();
		} catch (Exception e) {
			System.out.println("Error");
		}
	}
	
	public static void serializeXML(Scheduler sch, String filename) {
		filename += ".xml";
		
		String p = "C:\\Users\\1\\eclipse-workspace\\OOP_labs\\XML\\";
		
		try {
			XMLEncoder e = new XMLEncoder(new BufferedOutputStream
					(new FileOutputStream(p + filename)));
			
			e.writeObject((Integer) sch.getEvents().size());
			
			for (Event x: sch.getEvents()) {
				e.writeObject(x);
			}
			
			e.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	
	public static void deserializeXML(Scheduler sch, String filename) {
		filename += ".xml";
		
		String p = "C:\\Users\\1\\eclipse-workspace\\OOP_labs\\XML\\";
		
		try {
			XMLDecoder d = new XMLDecoder(new BufferedInputStream
					(new FileInputStream(p + filename)));
			
			Integer size = (Integer) d.readObject();
			
			for (int i = 0; i < size; i++) {
				sch.addEvent((Event) d.readObject());
			}
			
			d.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
}