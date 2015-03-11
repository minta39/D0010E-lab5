package event;

import model.CarFactory;
import model.CarWash;
import model.CarWashState;
import model.FastCarWash;
import model.SimState;
import model.SlowCarWash;

public class ArriveEvent extends Event {
	FastCarWash fCW;
	SlowCarWash sCW;
	CarWashState state;
	EventQueue eventQueue;
	CarFactory carFactory;
	


	public ArriveEvent(double time, String s,EventQueue eventQueue, CarWashState state) {
		super(time,s);
		this.eventQueue = eventQueue;
		this.state = state;	
		this.carFactory = state.getCarFactory();
	}
		/**
		 * updates state and queue
		 */
	public void execEvent(SimState sState,EventQueue eventQueue) {
		
		
		state.setEvent(this);
		if(state.getCarQueueSize()!=0 && state.emptySlowCarWashes() != 0 && state.emptyFastCarWashes() != 0){
			createNextEvent(state.getSlowTime(),new LeaveEvent(state.getSlowTime(),"Leave",state.addCar((state.getCarNRemove(0)))),eventQueue);
		}
		else{
			CarWash carWash = state.addCar(carFactory.createCar());
			
			if(carWash != null){
				
				if(this.state.emptyFastCarWashes()!=0){
					//System.out.println("Fast");
					createNextEvent(state.getFastTime(),new LeaveEvent(state.getFastTime(),"Leave",carWash),eventQueue);
				}
				else if(this.state.emptySlowCarWashes()!=0){
					//System.out.println("Slow");
					createNextEvent(state.getSlowTime(),new LeaveEvent(state.getSlowTime(),"Leave",carWash),eventQueue);
				}
				else{
					state.addCar(carFactory.createCar());
				}
			}
		}
	}
	
	@Override
	public void createNextEvent(double time, Event event,EventQueue eventQueue) {
		createNewEvent(eventQueue.getSortedSequence(),event);
	}
	
	/**
	 * not used
	 * @return
	 */
	public double fastCWorSlowCW(){
		return (this.state.addCar(carFactory.createCar())) == this.fCW ? this.state.getFastTime() : this.state.getSlowTime();
	}
}
