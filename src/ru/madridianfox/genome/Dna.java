package ru.madridianfox.genome;

import ru.madridianfox.world.things.Bot;

import java.util.*;

public class Dna {
    private List<Gene> genes;

    public Dna(List<Gene> genes){
        this.genes = genes;
    }
    /**
     * Получить неточную копию ДНК
     * @return копия ДНК
     */
    public Dna replicate(){
        List<Gene> new_genes = new ArrayList<>();
        for(Gene gene: genes){
            if(Math.random() > 0.02f){
                new_genes.add(gene.replicate());
            }
        }
//        if(Math.random() < 0.02f){
//            new_genes.add(randomGene().makeRandomGene(this));
//        }
        return new Dna(new_genes);
    }

    protected Gene randomGene(){
        return genes.get(new Random().nextInt(genes.size()));
    }

    /**
     * Сконструировать организм на основании данных ДНК
     * @return бот
     */
    public Bot makeOrganizm(){
        BotFabric fabric = new BotFabric();
        for(Gene gene: genes){
            gene.charge(fabric);
        }
        return fabric.makeBot(this);
    }
    public List<Gene> getGenes() {
        return genes;
    }
}
