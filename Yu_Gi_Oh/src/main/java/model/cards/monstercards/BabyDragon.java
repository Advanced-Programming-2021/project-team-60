package model.cards.monstercards;

public class BabyDragon extends MonsterCard{
    public BabyDragon(String name, String description, int level, int attackPoint, int defencePoint,int price) {
        super(name, description, level, attackPoint, defencePoint,price);
        this.attribute = Attribute.WIND;
        this.cardType = CardType.NORMAL;
        this.monsterType = MonsterType.DRAGON;
    }

    public void action(MonsterCard monster){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new BabyDragon(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
