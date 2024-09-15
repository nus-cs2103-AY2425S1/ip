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

    /**
     * Creates a new Pikappi object.
     */
    public void loadTasks() throws PikappiException {
        tasks = storage.load();
    }

    /**
     * Returns the response from Pikappi to user input.
     *
     * @param input User input
     * @return Response to user input
     * @throws PikappiException If there is an exception thrown from Pikappi
     */
    public String getResponse(String input) throws PikappiException {
        storage.save(tasks);
        Command c = parser.parse(input);
        return c.execute(tasks, ui, storage);
    }
}
