/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		database = new FacePamphletDatabase();
		canvas = new FacePamphletCanvas();
		add(canvas);
		initWest();
		initNorth();
		addActionListeners(this);
		
		// You fill this in
    }
	
	private void initNorth() {
		name = new JTextField (TEXT_FIELD_SIZE);
		add(new JLabel ("Name"),NORTH);
		add(name,NORTH);
		name.setActionCommand("Lookup");
		add(new JButton("Add"), NORTH);
		add(new JButton("Delete"), NORTH);
		add(new JButton("Lookup"), NORTH);
		name.addActionListener(this);
		
	}
	
	private void initWest() {
		addStatusField();
		addPictureField();
		addFriendField();
		
	}
	
	private void addFriendField() {
		friend = new JTextField(TEXT_FIELD_SIZE);
		add(friend, WEST);
		friend.setActionCommand("Add Friend");
		add( new JButton("Add Friend"), WEST);
		friend.addActionListener(this);
		
	}
	
	private void addPictureField() {
		picture = new JTextField(TEXT_FIELD_SIZE);
		add(picture, WEST);
		picture.setActionCommand("Change Picture");
		add( new JButton("Change Picture"), WEST);
		add( new JLabel(EMPTY_LABEL_TEXT), WEST);
		picture.addActionListener(this);
		
	}
	

	private void addStatusField() {
		status = new JTextField(TEXT_FIELD_SIZE);
		add(status,WEST);
		status.setActionCommand("Change Status");
		add( new JButton("Change Status"),WEST);
		add( new JLabel(EMPTY_LABEL_TEXT),WEST);
		status.addActionListener(this);
	}
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
    	switch (e.getActionCommand()) {
    	
    	case "Change Status":	if (!status.getText().isEmpty())
    								if (currentProfile != null){
    									currentProfile.setStatus(status.getText());
    									canvas.displayProfile(currentProfile);
    									canvas.showMessage("Set " + currentProfile.getName() + "'s status to: " + status.getText());
    								}
    								else canvas.showMessage("Please select profile");
    							break;
    									
    	case "Change Picture":	if (!picture.getText().isEmpty())
    								if (currentProfile != null) {
    									canvas.displayProfile(currentProfile);
							    		GImage image = null;
							    		try {
							    			image = new GImage(picture.getText());
							    			currentProfile.setImage(image);
							    			canvas.showMessage("Set " + currentProfile.getName() + "'s picture to: " + picture.getText());
							    			canvas.displayProfile(currentProfile);
							    		} catch (ErrorException ex) {
							    			canvas.showMessage("Image with name " + picture.getText() + " cannot be found.");
						    			}
    								}
    								else canvas.showMessage("Please select profile");
    							break;
    									
    	case "Add Friend":		if (!friend.getText().isEmpty())
    								if (currentProfile != null) {
    									canvas.displayProfile(currentProfile);
    									if (database.getProfile(friend.getText()) != null){
    										if (currentProfile.addFriend(friend.getText())){
    											database.getProfile(friend.getText()).addFriend(currentProfile.getName());
    											canvas.showMessage("Added " + friend.getText() + " to " + currentProfile.getName() + "'s Friend List");
    											canvas.displayProfile(currentProfile);
    										}
    										else canvas.showMessage("This friend already exists in your profile");
    									}
    									else canvas.showMessage("This profile is not valid");
    								}
    								else canvas.showMessage("Please select profile");
    							break;
    										
    	case "Add":				if (!name.getText().isEmpty())
    								if (database.getProfile(name.getText()) == null) {
    									FacePamphletProfile newProfile = new FacePamphletProfile(name.getText());
    									database.addProfile(newProfile);
    									currentProfile = newProfile;
    									canvas.showMessage("New profile added " + newProfile.toString());
    									canvas.displayProfile(currentProfile);
    								}
    								else canvas.showMessage("Profile already exists"); 
    							break;
    							
    	case "Delete":			if (!name.getText().isEmpty())
    								if (database.getProfile(name.getText()) != null) {
    									database.deleteProfile(name.getText());
    									currentProfile = null;
    									canvas.displayProfile(currentProfile);
    									canvas.showMessage("Profile " + name.getText() + " was deleted.");
    								}
    								else canvas.showMessage("Profile with name " + name.getText() + " does not exist.");
    							break;
    	
    	case "Lookup":			if (!name.getText().isEmpty())
    								if (database.getProfile(name.getText()) != null){
    									canvas.showMessage("Looked up: " + database.getProfile(name.getText()).toString());
    									currentProfile = database.getProfile(name.getText());
    									canvas.displayProfile(currentProfile);
    								}
    								else canvas.showMessage("Profile with name " + name.getText() + " does not exist");
    							break;
    							
    	}
    	
		// You fill this in as well as add any additional methods
	}
    FacePamphletDatabase database;
    FacePamphletCanvas canvas;
    private JTextField status;
    private JTextField picture;
    private JTextField friend;
    private JTextField name;
    private FacePamphletProfile currentProfile;

}
