package model.cards.monstercards;

public class HeraldOfCreation extends MonsterCard {
    public HeraldOfCreation(String name, String description, int level, int attackPoint, int defencePoint, int price) {
        super(name, description, level, attackPoint, defencePoint, price);
        this.attribute = Attribute.LIGHT;
        this.cardType = CardType.EFFECT;
        this.monsterType = MonsterType.SPELL_CASTER;
    }

    public void action(MonsterCard monster) {
        super.action(monster);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new HeraldOfCreation(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
