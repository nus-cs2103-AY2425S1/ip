package genji;

import genji.command.Command;
import genji.task.TaskList;

/**
 * The main class of the chatbot.
 * Includes constructor, run method, and main method
 */
public class Genji {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private static String FILE_PATH = "./data/Genji.txt";

    /**
     * Constructor of Genji
     * @param filePath place store and load the list
     */
    public Genji(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = storage.loadList();
    }

    /**
     * Runs the program with a welcome message
     * Receives user's commands and executes
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (GenjiException g) {
                ui.showError(g.toString());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Initializes the Genji instance and runs
     * @param args user's commands
     */
    public static void main(String[] args) {
        new Genji(FILE_PATH).run();
    }
}
