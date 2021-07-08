package model.cards.monstercards;

public class SpiralSerpent extends MonsterCard{
    public SpiralSerpent(String name, String description, int level, int attackPoint, int defencePoint, int price) {
        super(name, description, level, attackPoint, defencePoint, price);
        this.attribute = Attribute.WATER;
        this.cardType = CardType.NORMAL;
        this.monsterType = MonsterType.SEA_SERPENT;
    }

    public void action(MonsterCard monster){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new SpiralSerpent(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
