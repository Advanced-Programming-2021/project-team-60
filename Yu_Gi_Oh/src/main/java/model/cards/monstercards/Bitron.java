package model.cards.monstercards;

public class Bitron extends MonsterCard{
    public Bitron(String name, String description, int level, int attackPoint, int defencePoint,int price) {
        super(name, description, level, attackPoint, defencePoint,price);
        this.attribute = Attribute.EARTH;
        this.cardType = CardType.NORMAL;
        this.monsterType = MonsterType.CYBERSE;
    }

    public void action(MonsterCard monster){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new Bitron(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
