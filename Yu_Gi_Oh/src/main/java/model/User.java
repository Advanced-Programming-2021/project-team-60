package model;

import model.cards.Card;

import java.util.ArrayList;

public class User {
    private static ArrayList<User> allUsers;
    private String username;
    private String password;
    private String nickname;
    private int score;
    private int coins;
    private ArrayList<Card> userCards;
    public static User currentUser;
    public UserDecks userDecks;

    static {
        allUsers = new ArrayList<>();
    }

    public User(String username, String password, String nickname) {
        userCards = new ArrayList<>();
        userDecks = new UserDecks();
        setUsername(username);
        setPassword(password);
        setNickname(nickname);
        allUsers.add(this);
        setCoins(100000);
        setScore(0);
    }

    public static User getUserByUsername(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equalsIgnoreCase(username)) return user;
        }
        return null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setCoins(int coins) {
        this.coins += coins;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static void setAllUsers(ArrayList<User> allUsers) {
        User.allUsers = allUsers;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }


    public int getScore() {
        return score;
    }


    public int getCoins() {
        return coins;
    }

    public ArrayList<Card> getUserCards() {
        return userCards;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public UserDecks getUserDecks() {
        return userDecks;
    }

    public void addCard(Card card) {
        userCards.add(card);
    }

}