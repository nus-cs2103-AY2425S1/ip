package optimus;

import optimus.commands.Command;
import optimus.exceptions.OptimusExceptions;

/**
 * Handles logic
 */
public class Optimus {
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Constructor creates a Storage, TaskList and UI
     */
    public Optimus() {
        this.storage = new Storage();
        this.tasks = new TaskList(storage.load());
    }

    public String getResponse(String input) {
        assert input != null;
        try {
            Command c = Parser.parse(input);
            return c.execute(storage, tasks);
        } catch (OptimusExceptions e) {
            return e.getMessage();
        }
    }
}
