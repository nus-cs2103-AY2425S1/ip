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
     */
    public Genji() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.taskList = storage.loadList();
    }

    /**
     * Runs the program with a welcome message
     * Receives user's commands and executes
     */
    public void run() {
        ui.printWelcome();
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
        new Genji().run();
    }
}
