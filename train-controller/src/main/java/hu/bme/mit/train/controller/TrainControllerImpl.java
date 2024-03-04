package hu.bme.mit.train.controller;

import java.util.Random;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	boolean weatherBool=false;


	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		if(weatherBool == true){
			speedLimit*=0.7;
			this.speedLimit=speedLimit;

		}
		else{
			this.speedLimit = speedLimit;
			enforceSpeedLimit();
		}
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}

	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

	public void setBadWeather(boolean bad){
		 this.weatherBool= bad;
		
	}

	public boolean getWeatherBool()
	{
		return weatherBool;
	}
	public int getSpeedLimit(){
		return speedLimit;
	}

	
}

