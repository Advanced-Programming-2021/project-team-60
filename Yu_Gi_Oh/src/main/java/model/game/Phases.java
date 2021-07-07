package model.game;

public enum Phases {
    DRAW("draw phase"),
    STANDBY("standby phase"),
    MAIN_2("main phase 2"),
    BATTLE("battle phase"),
    MAIN_1("main phase 1"),
    END("end phase");

    private String string;

    Phases(String s) {
        this.string = s;
    }

    public Phases next() {
        switch (this) {
            case DRAW:
                return STANDBY;
            case STANDBY:
                return MAIN_1;
            case MAIN_1:
                return BATTLE;
            case MAIN_2:
                return END;
            case END:
                return DRAW;
            case BATTLE:
                return MAIN_2;
        }

        return null;
    }

    @Override
    public String toString() {
        return this.string;
    }
}
