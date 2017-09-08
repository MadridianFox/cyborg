package ru.madridianfox.ai.ann;

public class ConstDendrite implements DendriteInterface {
    private float constant;

    public ConstDendrite(float constant){
        this.constant = constant;
    }
    @Override
    public float signal() {
        return this.constant;
    }
}
