package lab8;

import java.util.ArrayList;

public class Event {
	private String name;
	private String startTime;
	private String duration;
	private String place;
	private String description; 
	private ArrayList<String> Participants = new ArrayList<>();
	
	public Event() {
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setStartTime(String s) {
		startTime = s;
	}
	
	public String getDuration() {
		return duration;
	}
	
	public void setDuration(String d) {
		duration = d;
	}
	
	public String getPlace() {
		return place;
	}
	
	public void setPlace(String p) {
		place = p;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String d) {
		description = d;
	}
	
	public ArrayList<String> getParticipants() {
		return Participants;
	}
	
	public void setParticipants(ArrayList<String> P) {
		Participants = P;
	}
	
	public void addParticipant(String name) {
		Participants.add(name);
	}
	
	public void removeLastParticipant() {
		if (Participants.size() > 0) {
			Participants.remove(Participants.size() - 1);
		} else {
			System.out.println("No participants");
		}
	}
	
	public void showInformation() {
		if (name != null) {
			System.out.println("Name- " + name);
		} else {
			System.out.println("*No name*");
		}
		if (startTime != null) {
			System.out.println("Start time- " + startTime);
		} else {
			System.out.println("*No start time*");
		}
		if (duration != null) {
			System.out.println("Duration- " + duration);
		} else {
			System.out.println("*No duration*");
		}
		if (place != null) {
			System.out.println("Place- " + place);
		} else {
			System.out.println("*No place*");
		}
		if (description != null) {
			System.out.println("Description- " + description);
		} else {
			System.out.println("*No description*");
		}
		if (Participants != null) {
			System.out.print("Participants- ");
			for (int i = 0, n = Participants.size(); i < n; i++) {
				System.out.print(Participants.get(i));				
				if (i != n - 1) {
					System.out.print(", ");
				}
			}	
		} else {
			System.out.println("*No participants*");
		}
		System.out.println();
	}
}