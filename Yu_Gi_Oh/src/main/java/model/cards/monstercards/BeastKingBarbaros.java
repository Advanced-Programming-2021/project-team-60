package model.cards.monstercards;

public class BeastKingBarbaros extends MonsterCard {

    public BeastKingBarbaros(String name, String description, int level, int attackPoint, int defencePoint, int price) {
        super(name, description, level, attackPoint, defencePoint, price);
        this.attribute = Attribute.EARTH;
        this.cardType = CardType.EFFECT;
        this.monsterType = MonsterType.BEAST_WARRIOR;
    }

    public void action(MonsterCard monster) {

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new BeastKingBarbaros(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
