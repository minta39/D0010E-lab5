package model;

public class CarFactory {
	
	private int carAmount = 0;
	
	public Car createCar(){
		Car car = new Car(carAmount);
		carAmount++;
		return car;
	}
	public void rejected(){
		carAmount--;
	}
	public int getCarAmount(){
		return carAmount;
	}
	
}
