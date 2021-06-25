package model.cards.spellcards;

import controller.DuelMenuController;
import model.cards.Position;
import model.cards.monstercards.MonsterCard;

public class MonsterReborn extends SpellCard {

    public MonsterReborn(String name, String description, int price) {
        super(name, description, price);
        this.status = Status.LIMITED;
        this.icon = Icon.NORMAL;
        this.type = CardType.SPELL;
    }

    public void action(MonsterCard monster){
        MonsterCard activeStongest = DuelMenuController.getInstance().game.getCurrentPlayer().getBoard().strongestMonsterInGraveyard();
        MonsterCard opponentStongest = DuelMenuController.getInstance().game.getOpponentPlayer().getBoard().strongestMonsterInGraveyard();

        if (opponentStongest.getAttackPoints() < activeStongest
                .getAttackPoints()) {

            DuelMenuController.getInstance().game.getCurrentPlayer().getBoard().putCardInMonsterZone(activeStongest);

            DuelMenuController.getInstance().game.getCurrentPlayer().getBoard().removeCardFromGraveyard(activeStongest);

            activeStongest.setPosition(Position.FIELD);

        } else {

            DuelMenuController.getInstance().game.getCurrentPlayer().getBoard().putFieldZone(opponentStongest);
            DuelMenuController.getInstance().game.getOpponentPlayer().getBoard().putFieldZone(opponentStongest);
            opponentStongest.setPosition(Position.FIELD);

        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new MonsterReborn(this.name, this.description, this.price);
    }
}
