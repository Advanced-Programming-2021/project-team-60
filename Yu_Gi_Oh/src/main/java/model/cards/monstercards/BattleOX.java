package model.cards.monstercards;

public class BattleOX extends MonsterCard{
    public BattleOX(String name, String description, int level, int attackPoint, int defencePoint,int price) {
        super(name, description, level, attackPoint, defencePoint,price);
        this.attribute = Attribute.EARTH;
        this.cardType = CardType.NORMAL;
        this.monsterType = MonsterType.BEAST_WARRIOR;
    }

    public void action(MonsterCard monster){

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
