/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	
	public void init() {
	    //You fill this in, along with any helper methods //
		database = new NameSurferDatabase(NAMES_DATA_FILE);
		graph = new NameSurferGraph();
		add(graph);
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		nameField = new JTextField(20);
		JLabel nameLabel = new JLabel("Name:"); 
		nameField.setActionCommand("Submit");
		add(nameLabel, SOUTH);
		add(nameField, SOUTH);
		add(new JButton("Submit"),SOUTH);
		add(new JButton("Clear"),SOUTH);
		addActionListeners();
		nameField.addActionListener(this);
	}
	
	

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Clear")) {
			graph.clear();
		}
		else if (cmd.equals("Submit")) {
			graph.addEntry(database.findEntry(nameField.getText()));
		}
	}
	
	/* ivars */
	JTextField nameField;
	private NameSurferGraph graph;
	private NameSurferDatabase database;
}
