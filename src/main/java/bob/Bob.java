package bob;

import storage.Storage;
import ui.UI;
import ui.Parser;
import ui.BotException;

/**
 * The Bob class is the main entry point for the Bob application.
 * It initializes the user interface and storage, and processes user input in a loop.
 */
public class Bob {
    public UI ui = new UI();
    public Storage storage = new Storage("src/main/Data/save.txt");

    public String getResponse(String userInput) {

        return Parser.parse(userInput, this.storage);
    }
}
