package model.cards.spellcards;

import model.cards.monstercards.MonsterCard;

public class Terraforming extends SpellCard{
    public Terraforming(String name, String description, int price) {
        super(name, description, price);
        this.status = Status.LIMITED;
        this.icon = Icon.NORMAL;
        this.type = CardType.SPELL;
    }

    public void action(MonsterCard monster){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new Terraforming(this.name, this.description, this.price);
    }
}
