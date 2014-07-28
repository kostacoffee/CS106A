/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		if (message != null) remove(message);
		message = new GLabel (msg);
		message.setFont(MESSAGE_FONT);
		add (message, getWidth()/2-message.getWidth()/2, getHeight()-BOTTOM_MESSAGE_MARGIN);
		// You fill this in
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		friendList.clear();
		if (profile != null) {
		displayName(profile.getName());
		displayPicture(profile.getImage());
		displayStatus(profile.getStatus());
		displayFriendList(profile.getFriends());
		}
	}
	
	private void displayName(String name) {
		profileName = new GLabel(name);
		profileName.setFont(PROFILE_NAME_FONT);
		add(profileName,LEFT_MARGIN,TOP_MARGIN+profileName.getHeight()/2);
	}
	
	private void displayPicture(GImage picture) {
		if (picture == null) {
			GRect noImgRect = new GRect(LEFT_MARGIN, TOP_MARGIN+profileName.getHeight()+IMAGE_MARGIN,IMAGE_WIDTH,IMAGE_HEIGHT );
			GLabel noImgL = new GLabel ("No Image");
			add(noImgRect);
			add(noImgL, noImgRect.getWidth()/2-noImgL.getWidth()/2, noImgRect.getHeight()/2-noImgL.getHeight()/2);
			return;
		}
		profilePicture = picture;
		profilePicture.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
		add(profilePicture, LEFT_MARGIN, TOP_MARGIN+profileName.getHeight()+IMAGE_MARGIN);
	}
	
	private void displayStatus(String status) {
		if (status == null) return;
		profileStatus = new GLabel (status);
		profileStatus.setFont(PROFILE_STATUS_FONT);
		add(profileStatus, LEFT_MARGIN, TOP_MARGIN+profileName.getHeight()+IMAGE_MARGIN +
			IMAGE_HEIGHT + STATUS_MARGIN);
	}
	
	private void displayFriendList(Iterator<String> friends) {
		drawFriendLabel();
		
		addToFriendList(friends);
		drawFriendList();

	}
	
	private void addToFriendList(Iterator<String> friends) {
		
		while (friends.hasNext()) {
			if (!friendList.contains(friends.hasNext())) {
				friendList.add( new GLabel(friends.next()));
			}
		}
	}
	
	private void drawFriendList() {
		int i;
		for (i=0; i < friendList.size(); i++) {
			add(friendList.get(i), friendLabel.getX(), friendLabel.getY() + friendLabel.getHeight()*(i+1));
		}
		
	}
	
	private void drawFriendLabel() {
		friendLabel = new GLabel("Friends:");
		friendLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(friendLabel,getWidth()/2, TOP_MARGIN+profileName.getHeight()+IMAGE_MARGIN + friendLabel.getAscent());
	}
	

	/** ivars */
	GLabel message;
	GLabel profileName;
	GImage profilePicture;
	GLabel profileStatus;
	GLabel friendLabel;
	ArrayList<GLabel> friendList = new ArrayList<GLabel>();
	
}
