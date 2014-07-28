/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		//	 You fill in the rest //
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		int i = 0;
		while(true) {
			if (entryGraphArr.size() <= i) break;
			remove(entryGraphArr.get(i));
			entry = null;
			i++;
		}
		entryGraphArr.clear();
		update();
		//	 You fill this in //
	}
	
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		if (entry == null) return;
		this.entry = entry;
		tracker.add(entry);
		update();
		// You fill this in //
	}
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		entryGraphArr.clear();
		drawBG();
		drawEntries();
		
		//	 You fill this in //
	}
	
	private void drawEntries () {
		if (entry == null) return;
		GCompound entryGraph = new GCompound();
		
		
		linesToCompound(entryGraph);
		entryGraphArr.add(entryGraph);
		
		int i;
		for (i = 0; i < entryGraphArr.size(); i++) {
			add(entryGraphArr.get(i));
		}
	}
	
	private void linesToCompound (GCompound compound) {
		int i;
		for (i = 0; i < tracker.size(); i++) {
		
			int j;
			for (j = 0; j < NDECADES-1; j++) {
				double y1 =  GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE*2) * tracker.get(i).getRank(j)/MAX_RANK;// GRAPH_MARGIN_SIZE +(tracker.get(i).getRank(j)*getHeight()/APPLICATION_HEIGHT);
				double y2 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE*2) * tracker.get(i).getRank(j+1)/MAX_RANK; // GRAPH_MARGIN_SIZE +(tracker.get(i).getRank(j+1)*getHeight()/APPLICATION_HEIGHT);
				GLine tempLine = new GLine (getWidth()/NDECADES * j, y1,
											getWidth()/NDECADES * (j+1),y2);
				
				GLabel tempLabel = new GLabel ("" + tracker.get(i).getName() + " " + tracker.get(i).getRank(j),
											   getWidth()/NDECADES * j,
											   (tempLine.getStartPoint().getY()));
				compound.add(tempLine);
				compound.add(tempLabel);
			}
		}
	}
	
	
	private void drawBG() {
		int i;
		for (i = 0; i < NDECADES; i++) {
			drawBGLines(i);
			drawBGLabels(i);
		} 
		add(new GLine(0, (GRAPH_MARGIN_SIZE), getWidth(),GRAPH_MARGIN_SIZE));
		add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE , getWidth(),getHeight() - GRAPH_MARGIN_SIZE));
		
		
	}
	private void drawBGLines(int i) {
		add(new GLine(getWidth()/NDECADES*i,0,getWidth()/NDECADES*i, getHeight()));
	}
	private void drawBGLabels (int i) {
		add(new GLabel ("Year " + (1900 + i*10)), getWidth()/11*i ,getHeight()- GRAPH_MARGIN_SIZE*0.25);
		
	}	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	private NameSurferEntry entry;
	private ArrayList<GCompound> entryGraphArr = new ArrayList<GCompound>();
	private ArrayList<NameSurferEntry> tracker = new ArrayList<NameSurferEntry>();
}
