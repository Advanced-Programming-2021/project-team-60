package model.cards.monstercards;

public class TheTricky extends MonsterCard{
    public TheTricky(String name, String description, int level, int attackPoint, int defencePoint, int price) {
        super(name, description, level, attackPoint, defencePoint, price);
        this.attribute = Attribute.WIND;
        this.cardType = CardType.EFFECT;
        this.monsterType = MonsterType.SPELL_CASTER;
    }

    public void action(MonsterCard monster){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new TheTricky(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
