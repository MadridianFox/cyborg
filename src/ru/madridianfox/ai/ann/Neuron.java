package ru.madridianfox.ai.ann;

import java.util.ArrayList;
import java.util.List;

public class Neuron implements NeuronInterface {
    private List<DendriteInterface> dendrites;
    public Neuron(){
        this(new ArrayList<>());
    }
    public Neuron(List<DendriteInterface> dendrites){
        this.dendrites = dendrites;
    }
    @Override
    public float axon() {
        float raw_signal = 0.f;
        for (DendriteInterface dendrite : this.dendrites) {
            raw_signal += dendrite.signal();
        }
        return (float)(1.f / (1.f + Math.exp(-raw_signal)));
    }

    @Override
    public void addDendrite(DendriteInterface dendrite) {
        dendrites.add(dendrite);
    }
}
