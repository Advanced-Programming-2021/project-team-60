package model.cards.monstercards;

public class ExploderDragon extends MonsterCard{
    public ExploderDragon(String name, String description, int level, int attackPoint, int defencePoint, int price) {
        super(name, description, level, attackPoint, defencePoint, price);
        this.attribute = Attribute.LIGHT;
        this.cardType = CardType.EFFECT;
        this.monsterType = MonsterType.DRAGON;
    }

    public void action(MonsterCard monster){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new ExploderDragon(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
