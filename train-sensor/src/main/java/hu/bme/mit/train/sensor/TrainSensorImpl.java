package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import java.time.LocalDate;
import com.google.common.collect.*;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;

	Table<LocalDate, Integer, Integer> tachograph  = HashBasedTable.create();

	public void tachograph(){
		tachograph.put(LocalDate.now(),controller.getReferenceSpeed(),user.getJoystickPosition());
	}

	public Table<LocalDate, Integer, Integer>  getTachograph(){
		return tachograph;
	}

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);
	}
	/* 
	@Override
	public int TachographReferenceSpeed(){
		return controller.getReferenceSpeed();
	}

	@Override
	public int TachographJoystickPosition(){
		return user.getJoystickPosition();
	}

	@Override
	public LocalDate TachographCurrentTime(){
		LocalDate myTime = LocalDate.now();
		return myTime;
	}*/


}
