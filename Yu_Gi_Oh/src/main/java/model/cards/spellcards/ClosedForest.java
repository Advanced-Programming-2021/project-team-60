package model.cards.spellcards;

import model.cards.monstercards.MonsterCard;

public class ClosedForest extends SpellCard{

    public ClosedForest(String name, String description, int price) {
        super(name, description, price);
        this.status = Status.UNLIMITED;
        this.icon = Icon.FIELD;
        this.type = CardType.SPELL;
    }

    public void action(MonsterCard monster){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new ClosedForest(this.name, this.description, this.price);
    }
}
