package model.cards.trapcards;

import model.cards.monstercards.MonsterCard;
import model.cards.spellcards.CardType;
import model.cards.spellcards.Icon;
import model.cards.spellcards.Status;

public class WallOfRevealingLight extends TrapCard{
    public WallOfRevealingLight(String name, String description) {
        super(name, description);
        this.icon = Icon.CONTINUOUS;
        this.status = Status.LIMITED;
        this.type = CardType.TRAP;
    }

    public void action(MonsterCard monster){

    }
}