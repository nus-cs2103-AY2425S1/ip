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

    public Ui getUi() {
        return ui;
    }

    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            return command.execute(storage, tasks, ui);
        } catch (RatchetException e) {
            return e.getMessage();
        }
    }
}
