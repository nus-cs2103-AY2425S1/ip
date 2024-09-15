package barcus;

import barcus.command.Command;
import barcus.exception.BarcusException;
import barcus.parser.Parser;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;
import barcus.ui.Ui;

/**
 * Main class for Barcus chatbot
 */
public class Barcus {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String commandType;

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
        this.commandType = "UnknownCommand";
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
     * Executes command and gets output
     * @param input full command from user
     * @return barcus's string output
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            commandType = c.getClass().getSimpleName();
            //System.out.println("Command type: " + commandType);
            return c.getString();
        } catch (BarcusException e) {
            commandType = "UnknownCommand";
            return "Uh oh, " + e.getMessage();
        }
    }

    public String getWelcome() {
        return "Beep bop! Hello I am Barcus, ready to be of assistance!\n"
                + "Write 'bye' to leave!\n";
    }

    /**
     * Returns last command type used
     * @return string of command type name
     */
    public String getCommandType() {
        return commandType;
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
