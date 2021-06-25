package model.cards.monstercards;

public class CrabTurtle extends MonsterCard {
    public CrabTurtle(String name, String description, int level, int attackPoint, int defencePoint, int price) {
        super(name, description, level, attackPoint, defencePoint, price);
        this.attribute = Attribute.WATER;
        this.cardType = CardType.RITUAL;
        this.monsterType = MonsterType.AQUA;
    }

    public void action(MonsterCard monster) {
        super.action(monster);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new CrabTurtle(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
