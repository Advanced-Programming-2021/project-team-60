package model.cards.spellcards;

import controller.DuelMenuController;
import model.cards.monstercards.MonsterCard;
import model.game.Player;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Raigeki extends SpellCard{
    public Raigeki(String name, String description, int price) {
        super(name, description, price);
        this.status = Status.LIMITED;
        this.icon = Icon.NORMAL;
        this.type = CardType.SPELL;
    }

    public void action(MonsterCard monster){
        Player opponent = DuelMenuController.getInstance().game.getOpponentPlayer();
        opponent.getBoard().putCardInGraveyard(monster);
    }



    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new Raigeki(this.name, this.description, this.price);
    }
}

