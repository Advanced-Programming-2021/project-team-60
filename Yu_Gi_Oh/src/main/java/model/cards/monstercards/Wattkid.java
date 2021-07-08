package model.cards.monstercards;

public class Wattkid extends MonsterCard{
    public Wattkid(String name, String description, int level, int attackPoint, int defencePoint, int price) {
        super(name, description, level, attackPoint, defencePoint, price);
        this.attribute = Attribute.LIGHT;
        this.cardType = CardType.NORMAL;
        this.monsterType = MonsterType.THUNDER;
    }

    public void action(MonsterCard monster){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new Wattkid(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
