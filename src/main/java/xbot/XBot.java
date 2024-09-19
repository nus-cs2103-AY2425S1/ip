package xbot;

import java.io.IOException;

import xbot.exception.XBotException;
import xbot.parser.Parser;
import xbot.storage.Storage;
import xbot.ui.Ui;

/**
 * The XBot class is the entry point of the chatbot application.
 * It initializes the main components of the chatbot, such as the task list,
 * storage, user interface, and parser. The class contains the main loop
 * that continuously processes user input until the user exits the program.
 */
public class XBot {
    private static TaskList list;
    private static Storage storage = new Storage();
    private static Ui ui = new Ui();
    private static Parser parser = new Parser();

    /**
     * Initializes a new instance of the XBot class.
     *
     * This constructor attempts to load the task list from storage. If an IOException occurs during loading,
     * the storage data is cleaned to prevent corrupted data.
     */
    public XBot() {
        try {
            this.list = storage.loadTask();
        } catch (IOException e) {
            storage.cleanData();
        }
    }

    /**
     * The main method that runs the XBot application.
     * It loads tasks from the storage, shows the welcome message,
     * and processes user commands in a loop until the user exits the program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
    }

    public String getResponse(String input) {
        String output;
        try {
            output = parser.processInput(input, list, ui, storage);
        } catch (XBotException e) {
            output = ui.mainErrorMessage(e);
        }
        return output;
    }
}
