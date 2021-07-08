package model.cards.monstercards;

public class GateGuardian extends MonsterCard{
    public GateGuardian(String name, String description, int level, int attackPoint, int defencePoint, int price) {
        super(name, description, level, attackPoint, defencePoint, price);
        this.attribute = Attribute.DARK;
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
        return new GateGuardian(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
