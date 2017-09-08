package ru.madridianfox.ai;

import ru.madridianfox.ai.sensors.Sensor;

import java.util.Map;

public class Mind {
    public String decide(Map<String, Sensor> sensors){
        String action = "mv";
        if(Math.random() > 0.9f){
            action = "tl";
        }
        if(Math.random() < 0.1f){
            action = "tr";
        }
        float color_sum = 0;
        for(Map.Entry<String,Sensor> entry: sensors.entrySet()){
            color_sum += entry.getValue().normalizedValue();
        }
        if(color_sum == 3){
            action = (Math.random() > 0.5 ? "tr": "tl");
        }
        if(sensors.get("eye_r").normalizedValue() == 55.f/255.f && sensors.get("eye_g").normalizedValue() == 177.f/255.f && sensors.get("eye_b").normalizedValue() == 88.f/255.f){
            action = "et";
        }
        return action;
    }
}
