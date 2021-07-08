package model.cards.monstercards;

import controller.Controller;
import controller.DuelMenuController;
import model.cards.Card;
import model.cards.Position;
import model.game.Game;
import model.game.Player;


public class MonsterCard extends Card {
    protected int level;
    protected int defencePoint;
    protected int attackPoint;
    protected boolean switchedPosition;
    protected boolean hasAttacked;
    protected Position position = Position.DEFENCE;
    protected CardType cardType;
    protected Attribute attribute;
    protected MonsterType monsterType;

    public MonsterCard(String name, String description, int level, int attackPoint, int defencePoint, int price) {
        super(name, description, price);
        setLevel(level);
        setAttackPoint(attackPoint);
        setDefencePoint(defencePoint);
        setHasAttacked(false);
        setSwitchedPosition(false);
    }


    public void attackMonster(MonsterCard target) {
        Player active = DuelMenuController.getInstance().game.getCurrentPlayer();
        Player opponent = DuelMenuController.getInstance().game.getOpponentPlayer();
        if (target.getPosition() == Position.ATTACK) {
            if (this.getAttackPoint() > target.getAttackPoint()) {
                int damage = this.getAttackPoint() - target.getAttackPoint();
                Controller.print("your opponent’s monster is destroyed and your opponent receives " +
                        +damage + " battle damage");
                int lp = opponent.getLifePoint();
                if (lp - damage <= 0) {
                    opponent.setLifePoint(0);
                    Game.getCurrentGame().goToNextRound(active, opponent);
                    return;
                }
                opponent.setLifePoint(lp - damage);
                opponent.getBoard().removeCardFromMonsterZone(target);
                opponent.getBoard().putCardInGraveyard(target);
            } else if (this.getAttackPoint() == target.getAttackPoint()) {
                active.getBoard().putCardInGraveyard(this);
                opponent.getBoard().putCardInGraveyard(target);
                Controller.print("both you and your opponent monster cards are destroyed and no one receives damage");
                opponent.getBoard().removeCardFromMonsterZone(target);
                opponent.getBoard().putCardInGraveyard(target);
                active.getBoard().removeCardFromMonsterZone(this);
                active.getBoard().putCardInGraveyard(this);
            } else {
                int damage = target.getAttackPoint() - this.getAttackPoint();
                active.getBoard().removeCardFromMonsterZone(this);
                active.getBoard().putCardInMonsterZone(this);
                Controller.print("Your monster card is destroyed and you received " + damage + " battle damage");
                int lp = active.getLifePoint();
                if (lp - damage <= 0) {
                    active.setLifePoint(0);
                    Game.getCurrentGame().goToNextRound(opponent, active);
                    return;
                }
                active.setLifePoint(lp - damage);
                active.getBoard().removeCardFromMonsterZone(this);
                active.getBoard().putCardInGraveyard(this);
            }
        } else if (this.getAttackPoint() > target.getDefencePoint()) {
            if (target.isHidden)
                Controller.print("opponent’s monster card was " + target.getName() + " and the defense position monster is destroyed");
            else Controller.print("the defense position monster is destroyed");
            opponent.getBoard().removeCardFromMonsterZone(target);
            opponent.getBoard().putCardInGraveyard(target);
        } else if (this.getAttackPoint() == target.getAttackPoint()) {
            this.setHidden(true);
            if (target.isHidden)
                Controller.print("opponent’s monster card was " + target.getName() + " and no card is destroyed");
            else Controller.print("no card is destroyed");
        } else {
            int damage = target.getDefencePoint() - this.getAttackPoint();
            int lp = active.getLifePoint();
            active.setLifePoint(lp - damage);
            this.setHidden(true);
            if (target.isHidden)
                Controller.print("opponent’s monster card was " + target.getName() + " and no card is destroyed and you received " + damage + " battle damage");
            else Controller.print("no card is destroyed and you received " + damage + " battle damage");
            if (lp - damage <= 0) {
                active.setLifePoint(0);
                Game.getCurrentGame().goToNextRound(opponent, active);
                return;
            }
            active.setLifePoint(lp - damage);
        }
    }

    public void switchPosition() {
        if (position == Position.ATTACK) {
            position = Position.DEFENCE;
            setHidden(false);
        } else {
            position = Position.ATTACK;
            setHidden(false);
        }
    }

    public void attackLifePoint() {
        int lifePoint = Game.getCurrentGame().getOpponentPlayer().getLifePoint();
        Controller.print("you opponent receives " + this.attackPoint + " battle damage");
        if ((lifePoint - this.attackPoint) <= 0) {
            Game.getCurrentGame().getOpponentPlayer().setLifePoint(0);
            Game.getCurrentGame().goToNextRound(Game.getCurrentGame().getCurrentPlayer(), Game.getCurrentGame().getOpponentPlayer());
            return;
        }
        DuelMenuController.getInstance().game.getOpponentPlayer().setLifePoint(lifePoint - this.getAttackPoint());
    }

    public void setHasAttacked(boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setDefencePoint(int defencePoint) {
        this.defencePoint = defencePoint;
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public void setSwitchedPosition(boolean switchedPosition) {
        this.switchedPosition = switchedPosition;
    }

    public boolean hasAttacked() {
        return hasAttacked;
    }

    public void setHidden(boolean hidden) {
        super.setHidden(hidden);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getLevel() {
        return level;
    }


    public int getDefencePoint() {
        return defencePoint;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public boolean isSwitchedPosition() {
        return switchedPosition;
    }

    public Position getPosition() {
        return position;
    }

    public CardType getCardType() {
        return cardType;
    }

    public boolean getHasAttacked() {
        return hasAttacked;
    }


    @Override
    public String toString() {
        return
                "Name: " + getName() +
                        "\nLevel: " + level +
                        "\nType: " + monsterType +
                        "\nATK: " + attackPoint +
                        "\nDEF: " + defencePoint +
                        "\nDescription:" + getDescription();
    }

    @Override
    public Object clone() {
        return new MonsterCard(this.name, this.description, this.level, this.attackPoint, this.defencePoint, this.price);
    }
}
