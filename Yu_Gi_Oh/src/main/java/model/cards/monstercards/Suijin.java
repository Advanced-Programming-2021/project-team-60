package model.cards.monstercards;

public class Suijin extends MonsterCard{
    public Suijin(String name, String description, int level, int attackPoint, int defencePoint, int price) {
        super(name, description, level, attackPoint, defencePoint, price);
        this.attribute = Attribute.WATER;
        this.cardType = CardType.NORMAL;
        this.monsterType = MonsterType.AQUA;
    }

    public void action(MonsterCard monster){
        super.action(monster);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new Suijin(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
