package model.cards.spellcards;

import model.cards.monstercards.MonsterCard;

public class RingOfDefence extends SpellCard{
    public RingOfDefence(String name, String description, int price) {
        super(name, description, price);
        this.status = Status.UNLIMITED;
        this.icon = Icon.QUICK_PLAY;
        this.type = CardType.SPELL;
    }

    public void action(MonsterCard monster){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new RingOfDefence(this.name, this.description, this.price);
    }
}
