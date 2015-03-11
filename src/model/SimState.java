package model;

import java.util.ArrayList;
import java.util.Observable;

import event.Event;

public abstract class SimState extends Observable {
	
	private double time;
	private Event currentEvent;
	
	public SimState(){
		time = System.currentTimeMillis();
	}
	
	public double getTime(){
		return (System.currentTimeMillis() - time)/1000;
	}
	
	public Event getCurrentEvent(){
		return currentEvent;
	}
	
	public void end(){
		setChanged();
		notifyObservers();
	}
	
	public void setEvent(Event event){
		this.currentEvent = event;
	}
	
}
