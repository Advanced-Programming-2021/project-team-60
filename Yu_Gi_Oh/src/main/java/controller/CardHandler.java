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

