package bob;

import bob.commands.Command;
import bob.data.BobException;
import bob.data.TaskList;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Represents the main program.
 */
public class Bob {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;


    /**
     * Creates a new instance of Bob.
     *
     * @param filePath the path to the data file where tasklist will be stored.
     */
    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BobException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Entry point of the program
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BobException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Bob("data/bob.txt").run();
    }
}
