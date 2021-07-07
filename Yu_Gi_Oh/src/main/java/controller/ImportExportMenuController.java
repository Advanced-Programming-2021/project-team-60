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
        File file = new File("Yu_Gi_Oh/src/main/resources/database/usersImportedCards/" + User.currentUser.getUsername() + ".json");
        if (CardFactory.getCardByCardName(cardName) != null) {
            try {
                file.createNewFile();
                String json = new String(Files.readAllBytes(Path.of(file.getPath())));
                ArrayList<Card> importedCards = (new Gson().fromJson(json, new TypeToken<List<Card>>() {
                }.getType()));
                if (importedCards == null) {
                    importedCards = new ArrayList<>();
                    importedCards.add(CardFactory.getCardByCardName(cardName));
                } else {
                    for (int i = 0; i < importedCards.size(); i++) {
                        if (importedCards.get(i).getName().equalsIgnoreCase(cardName)) break;
                        importedCards.add(CardFactory.getCardByCardName(cardName));
                    }
                }
                write(importedCards);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void exportCard(String cardName) {
        File file = new File("Yu_Gi_Oh/src/main/resources/database/usersImportedCards/" + User.currentUser.getUsername() + ".json");
        if (file.exists() && CardFactory.getCardByCardName(cardName) != null) {
            try {
                String json = new String(Files.readAllBytes(Path.of(file.getPath())));
                if (!json.isEmpty()) {
                    ArrayList<Card> importedCards = (new Gson().fromJson(json, new TypeToken<List<Card>>() {
                    }.getType()));
                    for (int i = 0; i < importedCards.size(); i++) {
                        if (importedCards.get(i).getName().equalsIgnoreCase(cardName)) importedCards.remove(i);
                    }
                    write(importedCards);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void write(ArrayList<Card> cards) throws IOException {
        FileWriter writer = new FileWriter("Yu_Gi_Oh/src/main/resources/database/usersImportedCards/" + User.currentUser.getUsername() + ".json");
        writer.write(new Gson().toJson(cards));
        writer.close();
    }
}