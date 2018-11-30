package lejos;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;

public class Sensor {

    public static EV3TouchSensor touchSensor = new EV3TouchSensor(SensorPort.S1);
    public static SensorMode sensorMode = touchSensor.getTouchMode();
    public static float[] pushed = new float[sensorMode.sampleSize()];

    public static EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S2);
    public static SensorMode irOpen = irSensor.getDistanceMode();
    public static float[] distance = new float[irOpen.sampleSize()];

}
