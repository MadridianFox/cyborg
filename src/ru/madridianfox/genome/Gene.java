package ru.madridianfox.genome;

public abstract class Gene {
    private String key;

    public Gene(String key){
        this.key = key;
    }
    /**
     * Получить неточную копию гена
     * @return ген
     */
    public abstract Gene replicate();

    /**
     * Занести информацию из гена в фабрику создания ботов
     * @param fabric фаьрика создания ботов
     */
    public abstract void charge(BotFabric fabric);

    /**
     * Сгенерировать новый ген
     * @param dna днк
     * @return новый ген
     */
    public abstract Gene makeRandomGene(Dna dna);

    public String key() {
        return key;
    }
}
