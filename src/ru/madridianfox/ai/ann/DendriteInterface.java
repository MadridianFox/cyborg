package ru.madridianfox.ai.ann;

public interface DendriteInterface {
    /**
     * Получить взвешенный сигнал от нейрона, который привязан к этому дендриту.
     * @return сигнал
     */
    float signal();
}
