package ollie;

import ollie.command.Command;
import ollie.exception.OllieException;

/**
 * Main Class. Ollie is the name of our chatbot. It contains the method
 * main and is executable.
 */
public class Ollie {
    // Constants
    private static final String filepath = "./data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Ollie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (OllieException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Starts the chatbot.
     *
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (OllieException e) {
                Ui.printResponse(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Ollie(filepath).run();
    }
}
