package model.cards.spellcards;

import controller.DuelMenuController;
import model.cards.monstercards.MonsterCard;

public class HarpiesFeatherDuster extends SpellCard{
    public HarpiesFeatherDuster(String name, String description, int price) {
        super(name, description, price);
        this.status = Status.LIMITED;
        this.icon = Icon.NORMAL;
        this.type = CardType.SPELL;
    }

    public void action(MonsterCard monster){
        DuelMenuController.getInstance().game.getCurrentPlayer().getBoard().putCardInGraveyard(monster);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new HarpiesFeatherDuster(this.name, this.description, this.price);
    }
}
