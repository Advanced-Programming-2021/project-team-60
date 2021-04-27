package model.cards.spellcards;

import model.cards.monstercards.MonsterCard;

public class ClosedForest extends SpellCard{

    public ClosedForest(String name, String description) {
        super(name, description);
        this.status = Status.UNLIMITED;
        this.icon = Icon.FIELD;
        this.type = CardType.SPELL;
    }

    public void action(MonsterCard monster){

    }
}
