package model.cards.monstercards;

public class HornImp extends MonsterCard{
    public HornImp(String name, String description, int level, int attackPoint, int defencePoint, int price) {
        super(name, description, level, attackPoint, defencePoint, price);
        this.attribute = Attribute.DARK;
        this.cardType = CardType.NORMAL;
        this.monsterType = MonsterType.FIEND;
    }

    public void action(MonsterCard monster){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new HornImp(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
