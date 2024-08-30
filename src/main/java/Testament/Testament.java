package Testament;

import Tasks.*;
import Ui.Ui;
import Storage.Storage;
import Parser.Parser;

/**
 * Main class for Testament chatbot.
 */
public class Testament {

    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;
    private final Parser parser;

    /**
     * Constructor for Testament.
     *
     * @param filePath filepath of memory file.
     */
    public Testament(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        taskList = storage.load();
        parser = new Parser(ui, taskList, storage);
    }

    /**
     * Starts running the testament chatbot.
     */
    public void run() {
        ui.welcome();
        parser.getUserInput();
    }

    public static void main(String[] args) {
        new Testament("Memory.TaskList.txt").run();
    }
}