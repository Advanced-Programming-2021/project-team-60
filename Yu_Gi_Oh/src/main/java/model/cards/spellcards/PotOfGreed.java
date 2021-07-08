package model.cards.spellcards;

import model.User;
import model.cards.monstercards.MonsterCard;

public class PotOfGreed extends SpellCard{
    public PotOfGreed(String name, String description, int price) {
        super(name, description, price);
        this.status = Status.LIMITED;
        this.icon = Icon.NORMAL;
        this.type = CardType.SPELL;
    }

    public void action(MonsterCard monster){
        User user = User.currentUser;

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object clone() {
        return new PotOfGreed(this.name, this.description, this.price);
    }
}

