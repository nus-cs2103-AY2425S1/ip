package hana;

import java.util.ArrayList;

import hana.command.Command;
import hana.parser.Parser;
import hana.storage.Storage;
import hana.tasklist.TaskList;
import hana.ui.Ui;

/**
 * The main class for the Hana chatbot.
 * Handles the initialization, running, and termination of the application.
 */
public class Hana {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Hana application instance.
     * Initializes the UI, task list, and storage.
     * Attempts to load tasks from storage.
     */
    public Hana() {
        ui = new Ui();
        tasks = new TaskList(new ArrayList<>());
        storage = new Storage(tasks.getTasks());
        try {
            storage.load();
        } catch (HanaException e) {
            ui.printError(e.getMessage());
        }
    }

    /**
     * Runs the main loop of the Hana application.
     * Displays greetings, processes user commands, and handles termination.
     */
    public void run() {
        ui.printGreetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                storage.save();
                isExit = c.isExit();
            } catch (HanaException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    /**
     * The entry point of the Hana application.
     * Creates a new Hana instance and runs the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Hana().run();
    }
}
