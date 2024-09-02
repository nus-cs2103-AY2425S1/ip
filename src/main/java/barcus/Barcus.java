package barcus;

import barcus.storage.Storage;
import barcus.tasklist.TaskList;
import barcus.ui.Ui;
import barcus.exception.BarcusException;
import barcus.command.Command;
import barcus.parser.Parser;

/**
 * Main class for Barcus chatbot
 */
public class Barcus {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Barcus, creates a new instance of Barcus with path to saved task data
     * @param filePath
     */
    public Barcus(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (BarcusException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Barcus instance
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
            } catch (BarcusException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main code that runs when barcus file runs
     * Creates a barcus instance
     * @param args
     */
    public static void main(String[] args) {
        new Barcus("./data/savedTasks.txt").run();
    }
}