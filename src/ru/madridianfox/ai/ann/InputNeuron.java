package ru.madridianfox.ai.ann;

public class InputNeuron implements InputNeuronInterface {
    private float value;
    public InputNeuron(float value){
        this.set(value);
    }
    @Override
    public void set(float value) {
        this.value = value;
    }

    @Override
    public float axon() {
        return this.value;
    }
}
