package Bob;

import Storage.Storage;
import UI.UI;
import UI.Parser;
import UI.BotException;

public class Bob {
    public static void main(String[] args) {
        UI ui = new UI();
        ui.showWelcomeMessage();

        Storage storage = new Storage("src/main/Data/save.txt");
        storage.loadData();

        boolean cont = true;

        while (cont) {
            try {
                String userInput = ui.getUserInput();
                ui.showSeparator();
                cont = Parser.parse(userInput, storage);
            } catch (BotException e) {
                ui.showErrorMessage(e.getMessage());
            } finally {
                ui.showNewLineSeparator();
            }
        }
    }
}
