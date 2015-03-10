package simulator;

import event.EventQueue;
import event.SortedSequence;
import event.StartEvent;
import gui.CarWashView;
import model.CarWashState;

public class Simulator {

	private boolean on;
	
	public Simulator(){
		on = false;
		
	}
	
	private void Driver(){
		int seed = 1234;
		int fastAmount = 3;
		int slowAmount = 4;
		int queueSize = 5;
		double fastLower = 2.8;
		double fastUpper = 5.7;
		double slowUpper = 6.7;
		double slowLower = 4.5;
		double lambda = 2.0;
		
		CarWashState cws = new CarWashState(seed, fastAmount, slowAmount, queueSize, fastLower, fastUpper, slowUpper, slowLower, lambda);
		CarWashView cwv = new CarWashView(cws);
		EventQueue eq = new EventQueue(new SortedSequence());
		eq.getSortedSequence().addNsort(new StartEvent(0,"Start"));;
		
		while(on){
			if(eq.hasNext()){
				if(eq.getSortedSequence().getElement(0).getEventTime() > cws.getTime()){
					eq.next();
				}
			}
		}
		
	}
	public void start(){
		on = true;
	}
	public void stop(){
		on = false;
	}
}
