package model.cards.monstercards;

public class DarkMagician extends MonsterCard{
    public DarkMagician(String name, String description, int level, int attackPoint, int defencePoint) {
        super(name, description, level, attackPoint, defencePoint);
        this.attribute = Attribute.DARK;
        this.cardType = CardType.NORMAL;
        this.monsterType = MonsterType.SPELL_CASTER;
    }

    public void action(MonsterCard monster){

    }
}
