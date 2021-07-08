package model.cards.monstercards;

import model.game.Game;

public class CommandKnight extends MonsterCard {
    public CommandKnight(String name, String description, int level, int attackPoint, int defencePoint,int price) {
        super(name, description, level, attackPoint, defencePoint,price);
        this.attribute = Attribute.FIRE;
        this.cardType = CardType.EFFECT;
        this.monsterType = MonsterType.WARRIOR;
    }



    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new CommandKnight(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
