package ru.madridianfox.genome;

import java.util.*;

public class NeuronGene extends Gene {
    private Map<String, Float> weights;

    public NeuronGene(String key, Map<String, Float> weights){
        super(key);
        this.weights = weights;
    }

    @Override
    public Gene replicate() {
        Map<String, Float> copy_weights = new HashMap<>();
        for(Map.Entry<String, Float> entry: weights.entrySet()){
            if(Math.random() > 0.5f){
                copy_weights.put(entry.getKey(), (float) Math.random());
            }else{
                copy_weights.put(entry.getKey(), entry.getValue());
            }
        }
        return new NeuronGene(key(), copy_weights);
    }

    @Override
    public void charge(BotFabric fabric) {
        fabric.addNeuron(key(), weights);
    }

    @Override
    public Gene makeRandomGene(Dna dna) {
        List<String> keys = new ArrayList<>(dna.getGenes().size());
        for(Gene gene: dna.getGenes()){
            keys.add(gene.key());
        }
        Random random = new Random();
        int num_weights = random.nextInt(10);
        Map<String, Float> weights = new HashMap<>();
        for(int i=0; i<num_weights; i++){
            String key;
            do{
                int index = random.nextInt(keys.size());
                key = keys.get(index);
            }while(weights.containsKey(key));
            weights.put(key, random.nextFloat());
        }
        return new NeuronGene(String.format("rand-%d%d%d", random.nextInt(10), random.nextInt(10), random.nextInt(10)),weights);
    }
}
