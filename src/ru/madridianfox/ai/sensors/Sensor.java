package ru.madridianfox.ai.sensors;

public abstract class Sensor {
    protected abstract int maxValue();
    protected abstract int value();
    public float normalizedValue(){
        return (float) this.value() / (float) this.maxValue();
    }
}
