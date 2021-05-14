package model.cards.spellcards;

import model.cards.monstercards.MonsterCard;

public class AdvancedRitualArt extends SpellCard{
    public AdvancedRitualArt(String name, String description, int price) {
        super(name, description, price);
        this.status = Status.UNLIMITED;
        this.icon = Icon.RITUAL;
        this.type = CardType.SPELL;
    }

    public void action(MonsterCard monster){

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
