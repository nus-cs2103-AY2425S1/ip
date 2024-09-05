package taskalyn;

import java.util.Objects;

/**
 * Starts the Taskalyn application.
 */
public class Taskalyn {
    private Ui ui;
    private Database database;
    private TaskManager taskManager;
    private Parser parser;
    private boolean isRunning;

    public Taskalyn() {
        this.ui = new Ui();
        this.database = new Database();
        this.taskManager = new TaskManager(database, ui);
        this.parser = new Parser(ui, taskManager);
        this.isRunning = true;
    }

    /**
     * Starts the Taskalyn application by initialising other classes.
     *
     * @param args Command-line arguments (unused)
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Database database = new Database();
        TaskManager taskManager = new TaskManager(database, ui);
        Parser parser = new Parser(ui, taskManager);
    }

    /**
     * Returns a response from Taskalyn for a given user input.
     *
     * @param input User input.
     * @return Response from Taskalyn.
     */
    public String getResponse(String input) {
        if (Objects.equals(input, "bye")) {
            return ui.showByeMessage();
        } else {
            return parser.parse(taskManager, input);
        }
    }
}
