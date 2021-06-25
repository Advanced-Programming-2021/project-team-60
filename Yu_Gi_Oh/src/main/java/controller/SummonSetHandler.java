package controller;

import model.cards.Card;
import model.cards.Location;
import model.cards.monstercards.CardType;
import model.cards.monstercards.MonsterCard;
import model.game.Game;
import model.game.Phases;

public abstract class SummonSetHandler {
    protected SummonSetHandler nextHandler;

    public void setNextHandler(SummonSetHandler nextHandler) {
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

class CardNotSelectHandler extends SummonSetHandler {
    @Override
    public boolean handle(Card card) {
        if (card == null) {
            Controller.print("no card is selected yet");
            return false;
        }
        return handleNext(card);
    }
}

class CardCantBeSummonedHandler extends SummonSetHandler {

    @Override
    public boolean handle(Card card) {
        if (!(card instanceof MonsterCard) ||
                (((MonsterCard) card).getCardType() == CardType.RITUAL) ||
                    card.getLocation() != Location.HAND) {
            Controller.print("you can’t summon this card");
            return false;
        }
        return handleNext(card);
    }
}

class CardCantBeSetHandler extends SummonSetHandler {

    @Override
    public boolean handle(Card card) {
        if (card.getLocation() != Location.HAND) {
            Controller.print("you can’t set this card");
            return false;
        }
        return handleNext(card);
    }
}

class MonsterSummonNotAllowedInCurrentPhase extends SummonSetHandler {
    @Override
    public boolean handle(Card card) {
        if (!(Game.getCurrentGame().getPhase() == Phases.MAIN_1 || Game.getCurrentGame().getPhase() == Phases.MAIN_2)) {
            Controller.print("action not allowed in this phase");
            return false;
        }
        return handleNext(card);
    }
}

class MonsterSetNotAllowedInCurrentPhase extends SummonSetHandler {
    @Override
    public boolean handle(Card card) {
        if (!(Game.getCurrentGame().getPhase() == Phases.MAIN_1 || Game.getCurrentGame().getPhase() == Phases.MAIN_2)) {
            Controller.print("you can’t do this action in this phase");
            return false;
        }
        return handleNext(card);
    }
}

class FullMonsterZoneHandler extends SummonSetHandler {
    Card[] monsterZone = Game.getCurrentGame().getCurrentPlayer().getBoard().getMonsterZone();

    @Override
    public boolean handle(Card card) {
        if ((monsterZone[1] != null) && (monsterZone[2] != null) && (monsterZone[3] != null)
                && (monsterZone[4] != null) && (monsterZone[5] != null)) {
            Controller.print("monster card zone is full");
            return false;
        }
        return handleNext(card);
    }
}

class CardAlreadySetOrSummoned extends SummonSetHandler {
    @Override
    public boolean handle(Card card) {
        if (Game.getCurrentGame().hasCardBeenSetOrSummoned()) {
            Controller.print("you already summoned/set on this turn");
            return false;
        }
        return handleNext(card);
    }
}
