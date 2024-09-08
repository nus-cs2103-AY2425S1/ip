package pikappi;

import pikappi.command.Command;
import pikappi.exception.PikappiException;

/**
 * Represents the main class that runs the program.
 */
public class Pikappi {
    protected static Ui ui = new Ui();
    protected static Storage storage = new Storage("data/pikappi.txt");
    protected static TaskList tasks = new TaskList();
    protected static Parser parser = new Parser();

    public void loadTasks() throws PikappiException {
        tasks = storage.load();
    }

    public String getResponse(String input) {
        try {
            storage.save(tasks);
            Command c = parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (PikappiException e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }
}
