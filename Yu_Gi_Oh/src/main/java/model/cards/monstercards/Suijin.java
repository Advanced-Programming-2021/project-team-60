package model.cards.monstercards;

public class Suijin extends MonsterCard{
    public Suijin(String name, String description, int level, int attackPoint, int defencePoint, int price) {
        super(name, description, level, attackPoint, defencePoint, price);
        this.attribute = Attribute.WATER;
        this.cardType = CardType.NORMAL;
        this.monsterType = MonsterType.AQUA;
    }

    public void action(MonsterCard monster){

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
