package slave;

import java.util.LinkedList;

/**
 * It is the main class for the program
 */
public class Slave {
    private static final LinkedList<Task> list = new LinkedList<>();
    private Storage storage;
    private Ui ui;
    private Parser parser;
    public static String SAVE_FILE_LOCATION = "./src/main/data/savefile.txt";

    /**
     * Creates a new Slave object which will save user interactions to
     * the specified file path
     *
     * @param filePath is the address of the save file
     */
    public Slave(String filePath) {
        storage = new Storage(list, filePath);
        ui = new Ui();
        parser = new Parser(list);
        storage.load();
    }

    /**
     * Returns Slave's response to the user's input.
     *
     * @param input is the user's input.
     * @return Slave's response to the user's input.
     */
    public String[] getResponse(String input) {
        return parser.handleUserInput(input);
    }

    /**
     * Saves any changes made to the list into the save file.
     */
    public void save() {
        storage.save();
    }

    public String[] getWelcomeMsg() {
        return ui.appWelcomeMsg();
    }

    /**
     * Loads all tasks from the save file into the list.
     */
    public void load() {
        storage.load();
    }
}
