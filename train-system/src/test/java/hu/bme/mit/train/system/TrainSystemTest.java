package hu.bme.mit.train.system;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.system.TrainSystem;
import java.time.LocalDate;
import com.google.common.collect.*;

public class TrainSystemTest {
	TrainController controller;
	TrainSensor sensor;
	TrainUser user;
	
	@Before
	public void before() {
		TrainSystem system = new TrainSystem();
		controller = system.getController();
		sensor = system.getSensor();
		user = system.getUser();
		sensor.overrideSpeedLimit(50);
		LocalDate curTime= LocalDate.now();
	}
	
	@Test
	public void OverridingJoystickPosition_IncreasesReferenceSpeed() {
		sensor.overrideSpeedLimit(10);

		Assert.assertEquals(0, controller.getReferenceSpeed());
		
		user.overrideJoystickPosition(5);

		controller.followSpeed();
		Assert.assertEquals(5, controller.getReferenceSpeed());
		controller.followSpeed();
		Assert.assertEquals(10, controller.getReferenceSpeed());
		controller.followSpeed();
		Assert.assertEquals(10, controller.getReferenceSpeed());
	}

	@Test
	public void OverridingJoystickPositionToNegative_SetsReferenceSpeedToZero() {
		user.overrideJoystickPosition(4);
		controller.followSpeed();
		user.overrideJoystickPosition(-5);
		controller.followSpeed();
		Assert.assertEquals(0, controller.getReferenceSpeed());
	}

	@Test
	public void TestWeather(){
		controller.setBadWeather(true);
		controller.setSpeedLimit(30);
		Assert.assertEquals(21, controller.getSpeedLimit());
	}

	@Test
	public void TestTachograph(){
		sensor.overrideSpeedLimit(10);
		user.overrideJoystickPosition(5);

		controller.followSpeed();

		user.overrideJoystickPosition(5);

		sensor.tachograph();


		sensor.overrideSpeedLimit(10);
		user.overrideJoystickPosition(6);

		controller.followSpeed();

		user.overrideJoystickPosition(7);

		sensor.tachograph();

		Assert.assertEquals(sensor.getTachograph().size(),2);
	}
	


	
}
