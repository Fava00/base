package hu.bme.mit.train.interfaces;

import java.time.LocalDate;
import com.google.common.collect.*;

public interface TrainSensor {

	int getSpeedLimit();

	void overrideSpeedLimit(int speedLimit);

	void tachograph();

	Table<LocalDate, Integer, Integer>  getTachograph();

}
