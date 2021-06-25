package model.cards.spellcards;

import controller.DuelMenuController;
import model.cards.Card;
import model.cards.monstercards.MonsterCard;

public class ChangeOfHeart extends SpellCard{
    public ChangeOfHeart(String name, String description, int price) {
        super(name, description, price);
        this.status = Status.LIMITED;
        this.icon = Icon.NORMAL;
        this.type = CardType.SPELL;
    }

    public void action(MonsterCard monster){
        DuelMenuController.getInstance().game.getOpponentPlayer().getBoard().removeCardFromMonsterZone(monster);
        monster.setHidden(false);

        DuelMenuController.getInstance().game.getOpponentPlayer().getBoard().putCardInMonsterZone(monster);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new ChangeOfHeart(this.name, this.description, this.price);
    }
}


