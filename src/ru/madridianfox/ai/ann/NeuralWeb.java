package ru.madridianfox.ai.ann;

import java.util.HashMap;
import java.util.Map;

public class NeuralWeb implements WebInterface{
    private Map<String, InputNeuronInterface> inputs;
    private Map<String, NeuronInterface> outputs;

    public NeuralWeb(Map<String, InputNeuronInterface> inputs, Map<String, NeuronInterface> outputs){
        this.inputs = inputs;
        this.outputs = outputs;
    }

    @Override
    public Map<String, Float> decide(Map<String, Float> inputs){
        Map<String, Float> out = new HashMap<>();
        inputs.forEach((code, in_signal) -> this.inputs.get(code).set(in_signal));
        this.outputs.forEach((code, out_neuron) -> out.put(code, out_neuron.axon()));
        return out;
    }
}
