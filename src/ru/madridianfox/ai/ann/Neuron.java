package ru.madridianfox.ai.ann;

public class Neuron implements NeuronInterface {
    private DendriteInterface[] dendrites;

    public Neuron(DendriteInterface[] dendrites){
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
}
