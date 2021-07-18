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
                card.getLocation() != Location.HAND) {
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

class CardPositionCantBeChanged extends CardHandler {
    @Override
    public boolean handle(Card card) {
        if (card.getLocation() != Location.MONSTER_ZONE) {
            Controller.print("you can’t change this card position");
            return false;
        }
        return handleNext(card);
    }
}

class CardCantBeFlipped extends CardHandler {
    @Override
    public boolean handle(Card card) {
        if (((MonsterCard) card).getPosition() != Position.DEFENCE || !card.isHidden() ||
                Game.getCurrentGame().isCardPutInThisTurn(card) || !Game.getCurrentGame().getCurrentPlayer().getBoard().isCardForPlayer(card)) {
            Controller.print("you can’t flip summon this card");
            return false;
        }
        return handleNext(card);
    }
}

class CardCantAttack extends CardHandler {
    @Override
    public boolean handle(Card card) {
        if (card.getLocation() != Location.MONSTER_ZONE || card.isHidden()) {
            Controller.print("you can’t attack with this card");
            return false;
        }
        return handleNext(card);
    }
}

class MonsterAttackNotAllowedInCurrentPhase extends CardHandler {
    @Override
    public boolean handle(Card card) {
        if (!(Game.getCurrentGame().getPhase() == Phases.BATTLE)) {
            Controller.print("you can’t do this action in this phase");
            return false;
        }
        return handleNext(card);
    }
}

class MonsterAlreadyAttacked extends CardHandler {
    @Override
    public boolean handle(Card card) {
        if (((MonsterCard) card).hasAttacked()) {
            Controller.print("this card already attacked");
            return false;
        }
        return handleNext(card);
    }
}


class CardCantAttackDirectly extends CardHandler {

    @Override
    public boolean handle(Card card) {

        MonsterCard[] monsterZone = Game.getCurrentGame().getOpponentPlayer().getBoard().getMonsterZone();
        boolean isMonsterZoneEmpty = true;
        for (MonsterCard monsterCard : monsterZone) {
            if (monsterCard != null) {
                isMonsterZoneEmpty = false;
                break;
            }
        }
        if (!isMonsterZoneEmpty) {
            Controller.print("you can’t attack the opponent directly");
            return false;
        }
        return handleNext(card);
    }
}

class CardEffectCantBeActivated extends CardHandler {
    @Override
    public boolean handle(Card card) {
        if (!(card instanceof SpellCard)) {
            Controller.print("activate effect is only for spell cards.");
            return false;
        }
        return handleNext(card);
    }
}

class EffectActivationNotAllowedInCurrentPhase extends CardHandler {
    @Override
    public boolean handle(Card card) {
        if (!(Game.getCurrentGame().getPhase() == Phases.MAIN_1 || Game.getCurrentGame().getPhase() == Phases.MAIN_2)) {
            Controller.print("you can’t activate an effect on this turn");
            return false;
        }
        return handleNext(card);
    }
}

class CardAlreadyActivated extends CardHandler {
    @Override
    public boolean handle(Card card) {
        if (card.getHasBeenActivated()) {
            Controller.print("you have already activated this card");
            return false;
        }
        return handleNext(card);
    }
}

class SpellPreparationNotDone extends CardHandler {
    @Override
    public boolean handle(Card card) {
        //todo
        return false;
    }
}

