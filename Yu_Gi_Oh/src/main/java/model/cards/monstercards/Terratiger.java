package model.cards.monstercards;

public class Terratiger extends MonsterCard{
    public Terratiger(String name, String description, int level, int attackPoint, int defencePoint, int price) {
        super(name, description, level, attackPoint, defencePoint, price);
        this.attribute = Attribute.EARTH;
        this.cardType = CardType.EFFECT;
        this.monsterType = MonsterType.WARRIOR;
    }

    public void action(MonsterCard monster){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new Terratiger(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
