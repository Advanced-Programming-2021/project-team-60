package model.cards.monstercards;

public class HeraldOfCreation extends MonsterCard{
    public HeraldOfCreation(String name, String description, int level, int attackPoint, int defencePoint) {
        super(name, description, level, attackPoint, defencePoint);
        this.attribute = Attribute.LIGHT;
        this.cardType = CardType.EFFECT;
        this.monsterType = MonsterType.SPELL_CASTER;
    }

    public void action(MonsterCard monster){

    }
}
