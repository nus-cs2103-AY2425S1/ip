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

    public static void main(String[] args) {
        new Ratchet().run();
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.read();
            Command command;
            try {
                command = parser.parse(input);
                command.execute(storage, tasks, ui);
                isExit = command.isExit();
            } catch (RatchetException e) {
                ui.error(e.getMessage());
            }
        }
    }
}
