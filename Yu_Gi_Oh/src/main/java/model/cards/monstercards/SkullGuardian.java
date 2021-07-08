package model.cards.monstercards;

public class SkullGuardian extends MonsterCard{
    public SkullGuardian(String name, String description, int level, int attackPoint, int defencePoint, int price) {
        super(name, description, level, attackPoint, defencePoint, price);
        this.attribute = Attribute.LIGHT;
        this.cardType = CardType.RITUAL;
        this.monsterType = MonsterType.WARRIOR;
    }

    public void action(MonsterCard monster){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new SkullGuardian(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
