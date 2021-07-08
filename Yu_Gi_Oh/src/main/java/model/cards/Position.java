package model.cards;

public enum Position {
    ATTACK("attack"),
    DEFENCE("defence");
    String string;
    Position(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
