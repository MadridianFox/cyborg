package ru.madridianfox.ai.ann;

public class ConstNeuron implements NeuronInterface {

    private float constant;

    public ConstNeuron(float constant){
        this.constant = constant;
    }

    @Override
    public float axon() {
        return this.constant;
    }

    @Override
    public void addDendrite(DendriteInterface dendrite) {}
}
