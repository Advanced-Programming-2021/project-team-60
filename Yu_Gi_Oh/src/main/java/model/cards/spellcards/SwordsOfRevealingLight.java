package model.cards.spellcards;

import model.User;
import model.cards.monstercards.MonsterCard;

public class SwordsOfRevealingLight extends SpellCard{
    public SwordsOfRevealingLight(String name, String description, int price) {
        super(name, description, price);
        this.status = Status.UNLIMITED;
        this.icon = Icon.NORMAL;
        this.type = CardType.SPELL;
    }
    public void action(MonsterCard monster){
        User user = User.currentUser;
        user.getBoard().addCardFromDeckToHand();
        user.getBoard().addCardFromDeckToHand();
    }


    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new SwordsOfRevealingLight(this.name, this.description, this.price);
    }
}
