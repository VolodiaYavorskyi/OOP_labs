package lab5;

import java.io.*;

public class Scheduler implements Serializable {
	private LinkedList<Event> Events;
	
	public Scheduler() {
	}
	
	public LinkedList<Event> getEvents() {
		return Events;
	}
	
	public void setEvents(LinkedList<Event> E) {
		Events = E;
	}
	
	public Event getEvent(int i) {
		return Events.get(i);
	}
	
	public void addEvent(Event e) {
		Events.insert(e);
	}
	
	public void removeEvent(int i) {
		Events.remove(i);
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
}