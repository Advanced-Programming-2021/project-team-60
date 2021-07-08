package model.cards.monstercards;

public class Texchanger extends MonsterCard{
    public Texchanger(String name, String description, int level, int attackPoint, int defencePoint , int price) {
        super(name, description, level, attackPoint, defencePoint, price);
        this.attribute = Attribute.DARK;
        this.cardType = CardType.EFFECT;
        this.monsterType = MonsterType.CYBERSE;
    }

    public void action(MonsterCard monster){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new Texchanger(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
