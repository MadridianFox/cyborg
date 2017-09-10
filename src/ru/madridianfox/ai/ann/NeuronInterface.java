package ru.madridianfox.ai.ann;

public interface NeuronInterface {
    /**
     * Получить сигнал с аксона.
     * @return сигнал
     */
    float axon();

    /**
     * Добавить связь с другим нейроном
     * @param dendrite дендрит
     */
    void addDendrite(DendriteInterface dendrite);
}
