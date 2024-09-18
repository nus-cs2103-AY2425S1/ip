package testament;

import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Main class for Testament chatbot.
 */
public class Testament {

    private static final String FILE_PATH = "memory/TaskList.txt";
    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;
    private final Parser parser;

    /**
     * Constructor for testament
     */
    public Testament() {
        storage = new Storage(FILE_PATH);
        ui = new Ui();
        taskList = storage.load();
        parser = new Parser(ui, taskList, storage);
    }

    /**
     * Starts running the testament chatbot.
     */

    public String getResponse(String input) {
        return parser.parse(input);
    }

    public String welcome() {
        return ui.welcome();
    }

    public static void main(String[] args) {
    }
}
