package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.User;
import model.cards.Card;
import model.cards.CardFactory;
import model.cards.monstercards.MonsterCard;
import model.cards.spellcards.SpellCard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ImportExportMenuController extends Controller {
    @Override
    public void processCommand(String command) {
        if (command.matches("import card .*"))
            importCard(command.replace("import card ", ""));
        else if (command.matches("export card .*"))
            exportCard(command.replace("export card ", ""));
        else print("invalid command");
    }

    private void importCard(String cardName) {
        File directory = new File("Yu_Gi_Oh/src/main/resources/database/usersImportedCards/" + User.currentUser.getUsername());
        if (!directory.exists()) directory.mkdir();
        File file = new File(directory.getPath() + "/" + cardName + ".json");
        if (CardFactory.getCardByCardName(cardName) != null) {
            try {
                file.createNewFile();
                FileWriter writer = new FileWriter(file.getPath());
                writer.write(new Gson().toJson(CardFactory.getCardByCardName(cardName)));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void exportCard(String cardName) {
        File directory = new File("Yu_Gi_Oh/src/main/resources/database/usersImportedCards/" + User.currentUser.getUsername());
        if (directory.exists()) {
            File file = new File(directory.getPath()  + "/"+ cardName + ".json");
            if (file.exists()) {
                try {
                    String json = new String(Files.readAllBytes(Path.of(file.getPath())));
                    if (!json.isEmpty()) {
                        Card card = (new Gson().fromJson(json, new TypeToken<Card>() {}.getType()));
                        print(CardFactory.getCardByCardName(cardName).toString());
                        file.delete();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}