package model.cards.monstercards;

//monsters
public class AlexandriteDragon extends MonsterCard{
    public AlexandriteDragon(String name, String description, int level, int attackPoint, int defencePoint,int price) {
        super(name, description, level, attackPoint, defencePoint,price);
        this.attribute = Attribute.LIGHT;
        this.cardType = CardType.NORMAL;
        this.monsterType = MonsterType.DRAGON;
    }

    public void action(MonsterCard monster){
        super.action(monster);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Object clone() {
        return new AlexandriteDragon(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
