package controller;

import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
class MenusControllerTest {
    @Test
    @DisplayName("checks if a user is created correctly")
    void checkCreateUser(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        LoginMenuController loginMenu = new LoginMenuController();
        loginMenu.createUser("create user --username testUser --nickname tester --password thisIsForTest");
        Assertions.assertNotNull(User.getUserByUsername("testUser"));
        Assertions.assertEquals(outContent.toString(), "user created successfully!\n");
    }
    @Test
    @DisplayName("Detects unsuccessful login")
    void loginUser (){
        User user = new User("testUser", "thisIsForTest", "tester");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        LoginMenuController loginMenu = new LoginMenuController();
        loginMenu.login("user login --username "+ user.getUsername()+" --password "+"thisIsFortest");
        Assertions.assertEquals(outContent.toString(), "Username and password didn’t match!\n");

    }
    @Test
    @DisplayName("Not making duplicate users")
    void duplicateUser(){
        User user = new User("testUser", "thisIsForTest", "tester");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        LoginMenuController loginMenu = new LoginMenuController();
        loginMenu.createUser("create user --username testUser --nickname tester --password thisIsForTest");
        Assertions.assertEquals(outContent.toString(), "user with username testUser already exists\n");
    }
    /*@Test
    @DisplayName("log out check")
    void checklogout(){
        MainMenuController mainMenu = new MainMenuController();
        User user = new User("testUser", "thisIsForTest", "tester");
        User.currentUser = user;
        mainMenu.logout();
        Assertions.assertNull(User.currentUser);

    }*/
    @Test
    @DisplayName("check changing password")
    void checkChangePassword(){
        ProfileMenuController profileMenu = new ProfileMenuController();
        User user = new User("testUser", "thisIsForTest", "tester");
        User.currentUser = user;
        profileMenu.changePassword("profile change --password --current thisIsForTest --new forTest");
        Assertions.assertEquals(User.currentUser.getPassword(), "forTest");
    }
    @Test
    @DisplayName("check changing nickname")
    void checkNickname(){
        ProfileMenuController profileMenu = new ProfileMenuController();
        User user = new User("testUser", "thisIsForTest", "tester");
        User.currentUser = user;
        profileMenu.changeNickname("change --nickname testingUser");
        Assertions.assertEquals(User.currentUser.getNickname(), "testingUser");
    }
    @Test
    @DisplayName("check scoreboard")
    void checkScoreboard(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        User user1 = new User("firstUser", "test", "first");
        User user2 = new User("secondUser", "test", "second");
        User user3 = new User("thirdUser", "test", "third");
        user1.setScore(50);
        user2.setScore(50);
        user3.setScore(20);
        ScoreBoardMenuController scoreboard = new ScoreBoardMenuController();
        scoreboard.showScoreboard();
        Assertions.assertEquals(outContent.toString(),"1- first: 50\n1- second: 50\n2- third: 20\n");
    }
    /*@Test
    @DisplayName("checks buying card process")
    void checkBuyCard(){

    }*/
}