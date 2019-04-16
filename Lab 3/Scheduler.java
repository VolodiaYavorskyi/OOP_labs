package lab3;

import java.util.ArrayList;
import java.io.*;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;

public class Scheduler implements Serializable {
	private ArrayList<Event> Events;
	
	public Scheduler() {
	}
	
	public ArrayList<Event> getEvents() {
		return Events;
	}
	
	public void setEvents(ArrayList<Event> E) {
		Events = E;
	}
	
	public Event getEvent(int i) {
		try {
			return Events.get(i);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Wrong index");
			
			return null;
		}
	}
	
	public void addEvent() {
		Events.add(new Event());
	}
	
	public void removeEvent(int i) {
		try {
			Events.remove(i);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Wrong index");
		}
	}
	
	public void clear() {
		Events.clear();
	}
	
	public void showEvents() {
		System.out.println("All events:");
		
		for (int i = 0, n = Events.size(); i < n; i++) {
			if (Events.get(i).getStartTime() != null) {
				System.out.print(Events.get(i).getStartTime());
			} else {
				System.out.print("*No start time*");
			}
			System.out.print("- ");
			if (Events.get(i).getName() != null) {
				System.out.print(Events.get(i).getName());
			} else {
				System.out.print("*No name*");
			}
			System.out.println(" (" + (i + 1) + ")");
		}
	}
	
	public void serialize(String filename, String foldername) {
		filename += ".xml";
		
		try {
			XMLEncoder e = new XMLEncoder(new BufferedOutputStream
					(new FileOutputStream(foldername + filename)));
			e.writeObject(this);
			e.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	
	public void deserialize(String filename, String foldername) {
		filename += ".xml";
		
		try {
			XMLDecoder d = new XMLDecoder(new BufferedInputStream
					(new FileInputStream(foldername + filename)));
			Scheduler temp = (Scheduler) d.readObject();
			this.Events = temp.Events;
			d.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
}