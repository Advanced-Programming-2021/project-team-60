package model.cards.monstercards;
import controller.DuelMenuController;
import model.Prototype;
import model.User;
import model.cards.Card;
import model.cards.Position;
import model.game.Player;
import view.menus.DuelMenu;

import java.lang.reflect.InvocationTargetException;


public class MonsterCard extends Card {
    protected int level;
    protected int defencePoint;
    protected int attackPoint;
    protected boolean switchedPosition;
    protected boolean attacked;
    protected Position position = Position.DEFENCE;
    protected CardType cardType;
    protected Attribute attribute;
    protected MonsterType monsterType;

    public MonsterCard(String name, String description, int level, int attackPoint, int defencePoint,int price){
        super(name, description,price);
        setLevel(level);
        setAttackPoint(attackPoint);
        setDefencePoint(defencePoint);
        setAttacked(false);
        setSwitchedPosition(false);
    }

    public void action(){
        attackLifePoint();
        this.setAttacked(true);
    }

    public void action(MonsterCard monster){
        if (monster != null) {
            attackMonster(monster);
        }
        this.setAttacked(true);
    }

    public void attackMonster(MonsterCard target){

        Player active = DuelMenuController.getInstance().game.getCurrentPlayer();
        Player opponent = DuelMenuController.getInstance().game.getOpponentPlayer();

        if (target.getPosition() == Position.ATTACK) {

            if (this.getAttackPoint() > target.getAttackPoint()) {

                int damage = this.getAttackPoint() - target.getAttackPoint();
                opponent.getBoard().putCardInGraveyard(target);
                int lp = opponent.getLifePoint();
                opponent.setLifePoint(lp - damage);

            } else if (this.getAttackPoint() == target.getAttackPoint()) {

                active.getBoard().putCardInGraveyard(this);
                opponent.getBoard().putCardInGraveyard(target);
                this.setAttacked(true);

            } else {

                int damage = target.getAttackPoint() - this.getAttackPoint();
                active.getBoard().removeCardFromMonsterZone(this);
                active.getBoard().putCardInMonsterZone(this);
                int lp = active.getLifePoint();
                active.setLifePoint(lp - damage);

            }

        } else {

            if (this.getAttackPoint() > target.getDefencePoint()) {
                opponent.getBoard().removeCardFromMonsterZone(target);
                opponent.getBoard().putCardInGraveyard(target);
                this.setAttacked(true);

            } else if (this.getAttackPoint() == target.getAttackPoint()) {

                this.setAttacked(true);
                this.setHidden(true);

            } else {

                int damage = target.getDefencePoint() - this.getAttackPoint();
                int lp = active.getLifePoint();
                active.setLifePoint(lp - damage);
                this.setHidden(true);

            }
        }

    }

    public void switchPosition(){
        if(position == Position.ATTACK){
            position = Position.DEFENCE;
            setHidden(true);
        }
        else{
            position = Position.ATTACK;
            setHidden(false);
        }
    }

    public void attackLifePoint(){
        int lifePoint = DuelMenuController.getInstance().game.getOpponentPlayer().getLifePoint();
        DuelMenuController.getInstance().game.getOpponentPlayer().setLifePoint(lifePoint - this.getAttackPoint());
    }

    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
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

    public boolean isAttacked() {
        return attacked;
    }

    public void setHidden(boolean hidden){
        super.setHidden(hidden);
    }

    public void setPosition(Position position){
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
