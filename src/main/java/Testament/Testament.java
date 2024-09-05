package testament;

import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Main class for Testament chatbot.
 */
public class Testament {

    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;
    private final Parser parser;
    private final String FILEPATH = "Memory.TaskList.txt";

    /**
     * Constructor for testament
     */
    public Testament() {
        storage = new Storage(FILEPATH);
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

    public String getResponse(String input) {
        return parser.parse(input);
    }

    public static void main(String[] args) {
        new Testament().run();
    }
}
