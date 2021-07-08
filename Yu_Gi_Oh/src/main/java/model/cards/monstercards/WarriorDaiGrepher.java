package model.cards.monstercards;

public class WarriorDaiGrepher extends MonsterCard{
    public WarriorDaiGrepher(String name, String description, int level, int attackPoint, int defencePoint, int price) {
        super(name, description, level, attackPoint, defencePoint, price);
        this.attribute = Attribute.EARTH;
        this.cardType = CardType.NORMAL;
        this.monsterType = MonsterType.WARRIOR;
    }

    public void action(MonsterCard monster){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new WarriorDaiGrepher(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
