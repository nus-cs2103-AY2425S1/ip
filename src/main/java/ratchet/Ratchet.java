package ratchet;

import ratchet.command.Command;
import ratchet.exception.RatchetException;
import ratchet.parser.Parser;
import ratchet.storage.Storage;
import ratchet.task.TaskList;
import ratchet.ui.Ui;

/**
 * Class that is the entry point for the program.
 */
public class Ratchet {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor for Ratchet
     */
    public Ratchet() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        parser = new Parser();
        storage.loadTasks(tasks);
    }

    /**
     * Returns Ui for Ratchet.
     *
     * @return Ui for ratchet.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Returns the response of Ratchet to the input.
     *
     * @param input User input.
     * @return String containing the response from Ratchet.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            return command.execute(storage, tasks, ui);
        } catch (RatchetException e) {
            return e.getMessage();
        }
    }
}
