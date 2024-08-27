package nether;

import nether.command.Command;
import nether.parser.Parser;
import nether.storage.Storage;
import nether.task.TaskList;

/**
 * Produces greetings for users and initializes chatbot.
 */

public class Nether {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    private static final String STORAGE_FILE_PATH = "./data/nether.txt";

    public Nether(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        tasks = new TaskList(storage.loadTasks());
    }

    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printHorizontalLine(); // show the divider line ("_______")
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                ui.printHorizontalLine();
            } catch (NetherException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    /**
     * The main method where the program starts.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Nether(STORAGE_FILE_PATH).run();
    }

}
