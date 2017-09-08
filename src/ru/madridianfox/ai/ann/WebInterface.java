package ru.madridianfox.ai.ann;

import java.util.Map;

public interface WebInterface {
    /**
     * Получить значения выходных нейровнов, поставив указанные значения во входные нейроны
     * @param inputs нормализхованные сигналы, каждый из которых лежит под кодом входного нейрона
     * @return нормализхованные сигналы, каждый из которых лежит под кодом выходного нейрона
     */
    public Map<String, Float> decide(Map<String, Float> inputs);
}
