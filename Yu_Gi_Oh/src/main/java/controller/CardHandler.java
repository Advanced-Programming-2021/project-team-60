package controller;
import model.cards.Card;
import model.cards.Location;
import model.cards.Position;
import model.cards.monstercards.CardType;
import model.cards.monstercards.MonsterCard;
import model.cards.spellcards.SpellCard;
import model.game.Game;
import model.game.Phases;

public abstract class CardHandler {
    protected CardHandler nextHandler;

    public void setNextHandler(CardHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected boolean handleNext(Card card) {
        if (nextHandler == null) {
            return true;
        }
        return nextHandler.handle(card);
    }

    public abstract boolean handle(Card card);
}

class CardNotSelect extends CardHandler {
    @Override
    public boolean handle(Card card) {
        if (card == null) {
            Controller.print("no card is selected yet");
            return false;
        }
        return handleNext(card);
    }
}

class CardCantBeSummoned extends CardHandler {
    @Override
    public boolean handle(Card card) {
        if (!(card instanceof MonsterCard) ||
                (((MonsterCard) card).getCardType() == CardType.RITUAL) ||
                card.getLocation() != Location.HAND ) {
            Controller.print("you can’t summon this card");
            return false;
        }
        return handleNext(card);
    }
}

class CardCantBeSet extends CardHandler {
    @Override
    public boolean handle(Card card) {
        if (card.getLocation() != Location.HAND) {
            Controller.print("you can’t set this card");
            return false;
        }
        return handleNext(card);
    }
}
class MonsterSummonNotAllowedInCurrentPhase extends CardHandler {
    @Override
    public boolean handle(Card card) {
        if (!(Game.getCurrentGame().getPhase() == Phases.MAIN_1 || Game.getCurrentGame().getPhase() == Phases.MAIN_2)) {
            Controller.print("action not allowed in this phase");
            return false;
        }
        return handleNext(card);
    }
}

class MonsterFlipSummonNotAllowedInCurrentPhase extends CardHandler {
    @Override
    public boolean handle(Card card) {
        if (!(Game.getCurrentGame().getPhase() == Phases.MAIN_1 || Game.getCurrentGame().getPhase() == Phases.MAIN_2)) {
            Controller.print("you can’t do this action in this phase");
            return false;
        }
        return handleNext(card);
    }
}

class CardSetOrChangeNotAllowedInCurrentPhase extends CardHandler {
    @Override
    public boolean handle(Card card) {
        if (!(Game.getCurrentGame().getPhase() == Phases.MAIN_1 || Game.getCurrentGame().getPhase() == Phases.MAIN_2)) {
            Controller.print("you can’t do this action in this phase");
            return false;
        }
        return handleNext(card);
    }
}
class FullMonsterZone extends CardHandler {
    Card[] monsterZone = Game.getCurrentGame().getCurrentPlayer().getBoard().getMonsterZone();

    @Override
    public boolean handle(Card card) {
        if ((monsterZone[0] != null) && (monsterZone[1] != null) && (monsterZone[2] != null)
                && (monsterZone[3] != null) && (monsterZone[4] != null)) {
            Controller.print("monster card zone is full");
            return false;
        }
        return handleNext(card);
    }
}


class FullSpellAndTrapZone extends CardHandler {
    Card[] spellAndTrapZone = Game.getCurrentGame().getCurrentPlayer().getBoard().getSpellAndTrapZone();

    @Override
    public boolean handle(Card card) {
        if ((spellAndTrapZone[1] != null) && (spellAndTrapZone[2] != null) && (spellAndTrapZone[3] != null)
                && (spellAndTrapZone[4] != null) && (spellAndTrapZone[0] != null)) {
            Controller.print("spell card zone is full");
            return false;
        }
        return handleNext(card);
    }
}

class CardAlreadySetOrSummoned extends CardHandler {
    @Override
    public boolean handle(Card card) {
        if (Game.getCurrentGame().hasCardBeenSetOrSummoned()) {
            Controller.print("you already summoned/set on this turn");
            return false;
        }
        return handleNext(card);
    }
}

