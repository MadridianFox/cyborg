package ru.madridianfox.ai.ann;

public class Dendrite implements DendriteInterface {
    private NeuronInterface neuron;
    private float weight;

    public Dendrite(NeuronInterface neuron, float weight){
        this.neuron = neuron;
        this.weight = weight;
    }
    @Override
    public float signal() {
        return this.neuron.axon() * this.weight;
    }
}
