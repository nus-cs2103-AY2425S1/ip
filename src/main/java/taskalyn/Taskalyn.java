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

    /**
     * Constructs the Taskalyn application with UI, Database, Parser, and TaskManager objects.
     */
    public Taskalyn() {
        this.ui = new Ui();
        this.database = new Database();
        this.taskManager = new TaskManager(database, ui);
        this.parser = new Parser(ui, taskManager);
    }

    /**
     * Returns a response from Taskalyn for a given user input.
     *
     * @param input User input.
     * @return Response from Taskalyn.
     */
    public String getResponse(String input) {
        if (Objects.equals(input.trim(), "bye")) {
            return ui.showByeMessage();
        } else {
            return parser.parse(taskManager, input);
        }
    }
}
