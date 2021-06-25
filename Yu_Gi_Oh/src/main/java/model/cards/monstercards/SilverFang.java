package model.cards.monstercards;

public class SilverFang extends MonsterCard{
    public SilverFang(String name, String description, int level, int attackPoint, int defencePoint, int price) {
        super(name, description, level, attackPoint, defencePoint, price);
        this.attribute = Attribute.EARTH;
        this.cardType = CardType.NORMAL;
        this.monsterType = MonsterType.BEAST;
    }

    public void action(MonsterCard monster){
        super.action(monster);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new SilverFang(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
