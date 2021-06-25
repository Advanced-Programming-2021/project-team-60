package model.cards.monstercards;

public class SlotMachine extends MonsterCard{
    public SlotMachine(String name, String description, int level, int attackPoint, int defencePoint, int price) {
        super(name, description, level, attackPoint, defencePoint, price);
        this.attribute = Attribute.DARK;
        this.cardType = CardType.NORMAL;
        this.monsterType = MonsterType.MACHINE;
    }

    public void action(MonsterCard monster){
        super.action(monster);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new SlotMachine(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
