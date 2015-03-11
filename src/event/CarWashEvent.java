package event;

import model.CarWash;
import model.SimState;

public class CarWashEvent extends Event{
	EventQueue eventQueue;
	private CarWash carWash;
	private double washTime;
	public CarWashEvent(double time,String s, CarWash carWash){
		super(time,s);
		this.washTime = time;
		this.carWash = carWash;
		
	}
	
	public void execEvent(SimState state,EventQueue eventQueue){
		state.setEvent(this);
		cleanCar();
		createNextEvent(washTime,new LeaveEvent(washTime, "Leave"),eventQueue);
	}

	private void cleanCar(){
		carWash.cleanCar();
	}

	@Override
	public void createNextEvent(double time, Event event,EventQueue eventQueue) {
		
		createNewEvent(eventQueue.getSortedSequence(),new LeaveEvent(time,"Leave"));
		
	}

}
