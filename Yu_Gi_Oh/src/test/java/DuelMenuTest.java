import controller.DuelMenuController;
import controller.LoginMenuController;
import model.Deck;
import model.User;
import model.game.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import view.menus.LoginMenu;

public class DuelMenuTest {
    @BeforeAll
    static void init() {
        LoginMenuController loginMenuController = new LoginMenuController();
        loginMenuController.createUser("user create --username harry --nickname theBoy --password head");
        loginMenuController.createUser("user create --username rom --nickname WeasleBe --password hermy");


    }

    @Test
    public void selectingCardTest() {

    }
}
