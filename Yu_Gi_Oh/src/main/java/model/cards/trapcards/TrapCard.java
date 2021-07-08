package model.cards.trapcards;
import model.cards.Card;
import model.cards.monstercards.MonsterCard;
import model.cards.spellcards.CardType;
import model.cards.spellcards.Icon;
import model.cards.spellcards.Status;

public abstract class TrapCard extends Card {
    protected Status status;
    protected Icon icon;
    protected CardType type;

    public TrapCard(String name, String description, int price){
        super(name,description,price);
    }

    public abstract void action(MonsterCard monster);

    @Override
    public String toString() {
        return "Name: " + getName() +
                "\nSpell" +
                "\nType=" + type +
                "\nDescription='" + getDescription();
    }

    @Override
    public Object clone() {
        return new TrapCard(this.name, this.description, this.price) {
            @Override
            public void action(MonsterCard monster) {
                this.action(monster);
            }
        };
    }
}
