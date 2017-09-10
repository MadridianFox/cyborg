package ru.madridianfox.ai;

import ru.madridianfox.ai.ann.NeuralWeb;
import ru.madridianfox.ai.ann.WebInterface;
import ru.madridianfox.ai.sensors.Sensor;

import java.util.HashMap;
import java.util.Map;

public class NeuralMind implements MindInterface {
    private WebInterface web;
    public NeuralMind(WebInterface web){
        this.web = web;
    }
    @Override
    public String decide(Map<String, Sensor> sensors) {
        Map<String,Float> signals = new HashMap<>();
        for(Map.Entry<String,Sensor> entry: sensors.entrySet()){
            signals.put(entry.getKey(), entry.getValue().normalizedValue());
        }
        Map<String, Float> action_weights = web.decide(signals);
        Map.Entry<String, Float> max = null;
        for(Map.Entry<String, Float> entry: action_weights.entrySet()){
            if(max == null){
                max = entry;
            }else{
                if(entry.getValue() > max.getValue()){
                    max = entry;
                }
            }
        }
        return max != null ? max.getKey() : "mv";
    }
}
