package gravitas;

import gravitas.command.Command;
import gravitas.exception.DukeException;
import gravitas.parser.Parser;
import gravitas.storage.Storage;
import gravitas.tasklist.TaskList;


/**
 * Represents the main class of the program.
 */
public class Gravitas {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Gravitas.
     *
     * @param filePath File path to load file
     */
    public Gravitas(String filePath) {
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the response from Gravitas.
     *
     * @param input User input
     * @return Response from Gravitas
     */
    public String getResponse(String input) {
        try {
            String response;
            Command c = Parser.parseCommand(input);
            response = c.executeCommand(tasks, storage);
            if (c.isExit()) {
                System.exit(0);
            }
            storage.saveTask(tasks);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
