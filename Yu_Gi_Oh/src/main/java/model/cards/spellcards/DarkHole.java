package model.cards.spellcards;

import controller.DuelMenuController;
import model.cards.monstercards.MonsterCard;

import java.util.ArrayList;
import java.util.Arrays;

public class DarkHole extends Raigeki{
    public DarkHole(String name, String description, int price) {
        super(name, description, price);
        this.status = Status.UNLIMITED;
        this.icon = Icon.NORMAL;
        this.type = CardType.SPELL;
    }

    public void action(MonsterCard monster){
        super.action(monster);
        DuelMenuController.getInstance().game.getCurrentPlayer().getBoard().putCardInGraveyard(monster);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new DarkHole(this.name, this.description, this.price);
    }
}
