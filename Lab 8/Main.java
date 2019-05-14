package lab8;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Scheduler scheduler = new Scheduler();
		scheduler.setEvents(new LinkedList<Event>());
		
		BorderPane root = new BorderPane();
		
		Scene scene = new Scene(root, 400, 450);
		
		VBox center = new VBox();
		center.setSpacing(10);
		
		Text title = new Text("\nScheduler");
		title.setFont(Font.font("Verdana", 25));
		BorderPane.setAlignment(title, Pos.BOTTOM_CENTER);
		
		root.setTop(title);
		
		Text bottom = new Text("\n");
		bottom.setFont(Font.font("Verdana", 15));
		bottom.setFill(Color.CORAL);
		BorderPane.setAlignment(bottom, Pos.CENTER);
		
		root.setBottom(bottom);
		
		Button b1 = new Button("Add event");
		b1.setPrefWidth(125);
		b1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				scheduler.addEvent(new Event());
				bottom.setText("Event was added\n");
			}
		});
		
		Button b2 = new Button("All events");
		b2.setPrefWidth(125);
		b2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (scheduler.getEvents().size() == 0) {
					bottom.setText("No events\n");
					
					return;
				}
				scheduler.showEvents();
				bottom.setText("Done\n");
			}
		});
		
		Button b3 = new Button("Event info");
		b3.setPrefWidth(125);
		b3.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (scheduler.getEvents().size() == 0) {
					bottom.setText("No events\n");
					
					return;
				}
				
				VBox eventsBox = new VBox();
				eventsBox.setAlignment(Pos.CENTER);
				eventsBox.setSpacing(10);
				
				int n = scheduler.getEvents().size();
				
				Button[] eventButtons = new Button[n];
				
				for (int i = 0; i < n; i++) {
					final int index = i;
					
					if (scheduler.getEvent(i).getName() == null) {
						eventButtons[i] = new Button("*No name*");
					} else {
						eventButtons[i] = new Button(scheduler.getEvent(i).getName());
					}
					
					eventButtons[i].setPrefWidth(125);
					eventButtons[i].setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							scheduler.getEvent(index).showInformation();
							bottom.setText("\n");
							primaryStage.setScene(scene);
						}
					});
					
					eventsBox.getChildren().add(eventButtons[i]);
				}
				
				Scene eventsScene = new Scene(eventsBox, 400, 450);
				
				primaryStage.setScene(eventsScene);
			}
		});
		
		Button b4 = new Button("Change");
		b4.setPrefWidth(125);
		b4.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (scheduler.getEvents().size() == 0) {
					bottom.setText("No events\n");
					
					return;
				}
				
				VBox eventsBox = new VBox();
				eventsBox.setAlignment(Pos.CENTER);
				eventsBox.setSpacing(10);
				
				int n = scheduler.getEvents().size();
				
				Button[] eventButtons = new Button[n];
				
				for (int i = 0; i < n; i++) {
					final int index = i;
					
					if (scheduler.getEvent(i).getName() == null) {
						eventButtons[i] = new Button("*No name*");
					} else {
						eventButtons[i] = new Button(scheduler.getEvent(i).getName());
					}
					
					eventButtons[i].setPrefWidth(125);
					eventButtons[i].setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							GridPane infoGrid = new GridPane();
							infoGrid.setAlignment(Pos.CENTER);
							infoGrid.setVgap(10);
							infoGrid.setHgap(10);
							
							Text wrongInput = new Text("");
							wrongInput.setFont(Font.font("Verdana", 15));
							wrongInput.setFill(Color.CORAL);
							infoGrid.add(wrongInput, 0, 8);
							
							Text eventName = new Text(scheduler.getEvent(index).getName());
							eventName.setFont(Font.font("Verdana", 25));
							GridPane.setHalignment(eventName, HPos.CENTER);
							infoGrid.add(eventName, 0, 0, 3, 1);
							
							Label name = new Label("Name:");
							infoGrid.add(name, 0, 1);
							TextField nameField = new TextField();
							infoGrid.add(nameField, 1, 1);
							Button nameButton = new Button("Change");
							nameButton.setOnAction(new EventHandler<ActionEvent>() {
								
								@Override
								public void handle(ActionEvent event) {
									scheduler.getEvent(index).setName(nameField.getText());
									wrongInput.setText("Success");
								}
							});
							infoGrid.add(nameButton, 2, 1);
							
							Label startTime = new Label("Start time:");
							infoGrid.add(startTime, 0, 2);
							TextField startTimeField = new TextField();
							infoGrid.add(startTimeField, 1, 2);
							Button startTimeButton = new Button("Change");
							startTimeButton.setOnAction(new EventHandler<ActionEvent>() {
								
								@Override
								public void handle(ActionEvent event) {
									if (InputChecker.correctStartTime(startTimeField.getText())) {
										scheduler.getEvent(index).setStartTime(startTimeField.getText());
										wrongInput.setText("Success");
									} else {
										wrongInput.setText("Invalid data");
									}
								}
							});
							infoGrid.add(startTimeButton, 2, 2);
							
							Label duration = new Label("Duration:");
							infoGrid.add(duration, 0, 3);
							TextField durationField = new TextField();
							infoGrid.add(durationField, 1, 3);
							Button durationButton = new Button("Change");
							durationButton.setOnAction(new EventHandler<ActionEvent>() {
								
								@Override
								public void handle(ActionEvent event) {
									if (InputChecker.correctDuration(durationField.getText())) {
										scheduler.getEvent(index).setDuration(durationField.getText());
										wrongInput.setText("Success");
									} else {
										wrongInput.setText("Invalid data");
									}
								}
							});
							infoGrid.add(durationButton, 2, 3);
							
							Label place = new Label("Place:");
							infoGrid.add(place, 0, 4);
							TextField placeField = new TextField();
							infoGrid.add(placeField, 1, 4);
							Button placeButton = new Button("Change");
							placeButton.setOnAction(new EventHandler<ActionEvent>() {
								
								@Override
								public void handle(ActionEvent event) {
									scheduler.getEvent(index).setPlace(placeField.getText());
									wrongInput.setText("Success");
								}
							});
							infoGrid.add(placeButton, 2, 4);
							
							Label description = new Label("Description:   ");
							infoGrid.add(description, 0, 5);
							TextField descriptionField = new TextField();
							infoGrid.add(descriptionField, 1, 5);
							Button descriptionButton = new Button("Change");
							descriptionButton.setOnAction(new EventHandler<ActionEvent>() {
								
								@Override
								public void handle(ActionEvent event) {
									scheduler.getEvent(index).setDescription(descriptionField.getText());
									wrongInput.setText("Success");
								}
							});
							infoGrid.add(descriptionButton, 2, 5);
							
							Label participant = new Label("Participant:");
							infoGrid.add(participant, 0, 6);
							TextField participantField = new TextField();
							infoGrid.add(participantField, 1, 6);
							Button addParticipant = new Button("Add");
							addParticipant.setOnAction(new EventHandler<ActionEvent>() {
								
								@Override
								public void handle(ActionEvent event) {
									if (InputChecker.correctParticipant(participantField.getText())) {
										scheduler.getEvent(index).addParticipant(participantField.getText());
										wrongInput.setText("Success");
									} else {
										wrongInput.setText("Invalid data");
									}
								}
							});
							infoGrid.add(addParticipant, 2, 6);
							Button removeParticipant = new Button("Remove last");
							removeParticipant.setOnAction(new EventHandler<ActionEvent>() {
								
								@Override
								public void handle(ActionEvent event) {
									if (scheduler.getEvent(index).getParticipants().size() == 0) {
										wrongInput.setText("No people");
										
										return;
									}
									
									scheduler.getEvent(index).removeLastParticipant();
									wrongInput.setText("Success");
								}
							});
							infoGrid.add(removeParticipant, 0, 7);
							
							Button submit = new Button("Submit");
							GridPane.setHalignment(submit, HPos.RIGHT);
							submit.setOnAction(new EventHandler<ActionEvent>() {
							
								@Override
								public void handle(ActionEvent arg0) {
									bottom.setText("Done\n");
									
									primaryStage.setScene(scene);
								}
							});
							
							infoGrid.add(submit, 1, 8);
							
							Scene infoScene = new Scene(infoGrid, 400, 450);
							
							primaryStage.setScene(infoScene);
						}
					});
					
					eventsBox.getChildren().add(eventButtons[i]);
				}
				
				Scene eventsScene = new Scene(eventsBox, 400, 450);
				
				primaryStage.setScene(eventsScene);
			}
		});
		
		Button b5 = new Button("Remove");
		b5.setPrefWidth(125);
		b5.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (scheduler.getEvents().size() == 0) {
					bottom.setText("No events\n");
					
					return;
				}
				
				VBox eventsBox = new VBox();
				eventsBox.setAlignment(Pos.CENTER);
				eventsBox.setSpacing(10);
				
				int n = scheduler.getEvents().size();
				
				Button[] eventButtons = new Button[n];
				
				for (int i = 0; i < n; i++) {
					final int index = i;
					
					if (scheduler.getEvent(i).getName() == null) {
						eventButtons[i] = new Button("*No name*");
					} else {
						eventButtons[i] = new Button(scheduler.getEvent(i).getName());
					}
					
					eventButtons[i].setPrefWidth(125);
					eventButtons[i].setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							scheduler.removeEvent(index);
							bottom.setText("Event was removed\n");
							primaryStage.setScene(scene);
						}
					});
					
					eventsBox.getChildren().add(eventButtons[i]);
				}
				
				Scene eventsScene = new Scene(eventsBox, 400, 450);
				
				primaryStage.setScene(eventsScene);
			}
		});
		
		Button b6 = new Button("Remove all");
		b6.setPrefWidth(125);
		b6.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (scheduler.getEvents().size() == 0) {
					bottom.setText("No events\n");
					
					return;
				}
				
				scheduler.clear();
				bottom.setText("Events were removed\n");
			}
		});
		
		Button b7 = new Button("Write to XML");
		b7.setPrefWidth(125);
		b7.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (scheduler.getEvents().size() == 0) {
					bottom.setText("No events\n");
					
					return;
				}
				
				VBox toXMLroot = new VBox();
				toXMLroot.setAlignment(Pos.CENTER);
				toXMLroot.setSpacing(10);
				
				Text toXMLTitle = new Text("Filename:");
				toXMLTitle.setFont(Font.font("Verdana", 25));
				toXMLroot.getChildren().add(toXMLTitle);
				
				TextField toXMLField = new TextField();
				toXMLField.setMaxWidth(150);
				toXMLroot.getChildren().add(toXMLField);
				
				Button toXMLButton = new Button("Write");
				toXMLButton.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						String p = "C:\\Users\\1\\eclipse-workspace\\OOP_labs\\XML\\";
						String filename = toXMLField.getText();
						
						if (!filename.contains(".xml")) {
							filename += ".xml";
						}
						
						try {
							XMLEncoder e = new XMLEncoder(new BufferedOutputStream
									(new FileOutputStream(p + filename)));
							e.writeObject(scheduler.getEvents());
							e.close();
							
							bottom.setText("Success\n");
							
							primaryStage.setScene(scene);
						} catch (FileNotFoundException e) {
							bottom.setText("File not found\n");
							
							primaryStage.setScene(scene);
						}
					}
				});
				toXMLroot.getChildren().add(toXMLButton);
				
				Scene toXMLScene = new Scene(toXMLroot, 400, 450);
				
				primaryStage.setScene(toXMLScene);
			}
		});
		
		Button b8 = new Button("Read from XML");
		b8.setPrefWidth(125);
		b8.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				File path = new File("C:\\Users\\1\\eclipse-workspace\\OOP_labs\\XML");
				String[] children = path.list();
				
				if (children.length == 0) {
					bottom.setText("No XML files\n");
					
					return;
				}
				
				VBox fromXMLroot = new VBox();
				fromXMLroot.setAlignment(Pos.CENTER);
				fromXMLroot.setSpacing(10);
				
				int n = children.length;
				
				Button[] Files = new Button[n];
				
				for (int i = 0; i < n; i++) {
					final int index = i;
					
					Files[i] = new Button(children[i]);
					Files[i].setPrefWidth(125);
					Files[i].setOnAction(new EventHandler<ActionEvent>() {
						
						@SuppressWarnings("unchecked")
						@Override
						public void handle(ActionEvent event) {
							String p = "C:\\Users\\1\\eclipse-workspace\\OOP_labs\\XML\\";
							String filename = children[index];
							
							try {
								XMLDecoder d = new XMLDecoder(new BufferedInputStream
										(new FileInputStream(p + filename)));
								
								scheduler.setEvents((LinkedList<Event>) d.readObject());
								
								d.close();
								
								bottom.setText("Success\n");
								
								primaryStage.setScene(scene);
							} catch (FileNotFoundException e) {
								bottom.setText("File not found\n");
								
								primaryStage.setScene(scene);
							}
						}
					});
					
					fromXMLroot.getChildren().add(Files[i]);
				}
				
				Scene fromXMLScene = new Scene(fromXMLroot, 400, 450);
				
				primaryStage.setScene(fromXMLScene);
			}
		});
		
		center.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8);
		
		center.setAlignment(Pos.CENTER);
		root.setCenter(center);
		
		primaryStage.setTitle("Scheduler");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}