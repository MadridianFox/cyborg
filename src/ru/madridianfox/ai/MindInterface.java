package ru.madridianfox.ai;

import ru.madridianfox.ai.sensors.Sensor;
import java.util.Map;

public interface MindInterface {
    String decide(Map<String, Sensor> sensors);
}
