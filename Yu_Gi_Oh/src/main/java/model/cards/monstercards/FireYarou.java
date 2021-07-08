package model.cards.monstercards;

public class FireYarou extends MonsterCard{
    public FireYarou(String name, String description, int level, int attackPoint, int defencePoint, int price) {
        super(name, description, level, attackPoint, defencePoint, price);
        this.attribute = Attribute.FIRE;
        this.cardType = CardType.NORMAL;
        this.monsterType = MonsterType.PYRO;
    }

    public void action(MonsterCard monster){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new FireYarou(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
