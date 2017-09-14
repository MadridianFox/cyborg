package ru.madridianfox.genome;

import ru.madridianfox.ai.MindInterface;
import ru.madridianfox.ai.NeuralMind;
import ru.madridianfox.ai.ann.*;
import ru.madridianfox.world.things.Bot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BotFabric {
    /**
     * Специальные входные нейроны, которые получают сигнал напрямую от сенсоров
     */
    private Map<String, InputNeuronInterface> inputs = new HashMap<>();
    /**
     * Выходные нейроны. На основании их сигналов будут приниматься решения
     */
    private Map<String, NeuronInterface> outputs = new HashMap<>();
    /**
     * Данные о всех нейронах сети
     */
    private Map<String, Map<String, Float>> neurons_data = new HashMap<>();
    /**
     * Пул нейронов, чтобы накапливать годные нейроны
     */
    private Map<String, NeuronInterface> neuron_pool = new HashMap<>();

    public BotFabric(){
        // red channel
        inputs.put("ernl", new InputNeuron()); inputs.put("ern", new InputNeuron()); inputs.put("ernr", new InputNeuron());
        inputs.put("erl", new InputNeuron()); /*##################################*/ inputs.put("err", new InputNeuron());
        inputs.put("erll", new InputNeuron()); inputs.put("erlll", new InputNeuron());inputs.put("errr", new InputNeuron());

        for(String key: inputs.keySet()){
            neurons_data.put(key, new HashMap<>());
        }
    }

    public void addNeuron(String key, Map<String,Float> weights){
        neurons_data.put(key, weights);
    }

    /**
     * Преобразовать накопленные данные о нейронах в нейросеть.
     * Если какой-то нейрон будет иметь вес, указывающий на нейрон, которого нет в накопленных данных,
     * то вес не сформируется. Т.е. сеть устойчива к удалению нейронов.
     * @return нейросеть
     */
    private WebInterface makeWeb(){
        List<String> output_keys = Arrays.asList(Bot.output_names);
        for(Map.Entry<String, Map<String, Float>> neuron_data: neurons_data.entrySet()){
            String key = neuron_data.getKey();
            NeuronInterface neuron = new Neuron();
            neuron_pool.put(key, neuron);
            if(output_keys.contains(key)){
                outputs.put(key, neuron);
            }
        }
        for(Map.Entry<String, Map<String, Float>> neuron_data: neurons_data.entrySet()){
            String key = neuron_data.getKey();
            Map<String, Float> weights = neuron_data.getValue();

            for(Map.Entry<String, Float> weight: weights.entrySet()){
                NeuronInterface neuron = neuron_pool.get(weight.getKey());
                if(neuron != null){
                    neuron_pool.get(key).addDendrite(new Dendrite(neuron, weight.getValue()));
                }
            }
        }

        return new NeuralWeb(inputs, outputs);
    }

    public Bot makeBot(Dna dna){
        MindInterface mind = new NeuralMind(makeWeb());
        return new Bot(dna, mind, new int[]{0, 128, 128});
    }
}
