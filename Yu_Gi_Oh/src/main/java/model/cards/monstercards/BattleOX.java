package model.cards.monstercards;

public class BattleOX extends MonsterCard{
    public BattleOX(String name, String description, int level, int attackPoint, int defencePoint,int price) {
        super(name, description, level, attackPoint, defencePoint,price);
        this.attribute = Attribute.EARTH;
        this.cardType = CardType.NORMAL;
        this.monsterType = MonsterType.BEAST_WARRIOR;
    }

    public void action(MonsterCard monster){
        super.action(monster);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new BattleOX(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
