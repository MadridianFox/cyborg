package tests.ai;

import org.junit.Test;
import ru.madridianfox.ai.ann.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AITest {
    @Test
    public void dendriteSignal(){
        DendriteInterface dendrite = new Dendrite(new ConstNeuron(2.0f), 5.0f);
        assertEquals(10.f, dendrite.signal(),0.0001f);
    }
    @Test
    public void neuronSignal(){
        List<DendriteInterface> dendrites = new ArrayList<>();
        dendrites.add(new ConstDendrite(0.2f));
        dendrites.add(new ConstDendrite(0.3f));
        dendrites.add(new ConstDendrite(0.4f));
        NeuronInterface neuron = new Neuron(dendrites);
        float out = (float)(1 / (1 + Math.exp(-0.2f - 0.3f - 0.4f)));
        assertEquals(out, neuron.axon(), 0.001f);
    }

    @Test
    public void inputSignal(){
        InputNeuronInterface in = new InputNeuron(0.99f);
        assertEquals(0.99f, in.axon(), 0.001f);
        in.set(0.88f);
        assertEquals(0.88f, in.axon(),0.001f);
    }

    @Test
    public void webSignals(){
        Map<String, InputNeuronInterface> inputs = new HashMap<>();
        Map<String, NeuronInterface> outputs = new HashMap<>();
        Map<String, Float> in_signals = new HashMap<>();

        for(int i = 1; i < 11; i++){
            in_signals.put("in_"+i, 10.01f/i);
            inputs.put("in_"+i, new InputNeuron(0.f));
            outputs.put("out_"+i, new ConstNeuron(1.f/i));
        }

        WebInterface web = new NeuralWeb(inputs, outputs) {
            @Override
            public Map<String, Float> decide(Map<String, Float> inputs) {
                return super.decide(inputs);
            }
        };

        Map<String, Float> out_signals = web.decide(in_signals);

        for(int i = 1; i < 11; i++){
            String code_in = "in_"+i;
            String code_out = "out_"+i;
            assertEquals(10.01f/i, inputs.get(code_in).axon(), 0.001f);
            assertEquals(1.f/i, out_signals.get(code_out), 0.001f);
        }
    }
}
