package bibi;

import bibi.task.TaskList;
import java.io.FileNotFoundException;

/**
 * Represents the chat bot.
 */
public class Bibi {
    /** The Ui instance that handles printing and reading console commands */
    private Ui ui;
    /** The list that contains all Tasks inside the save file */
    private TaskList tasks;
    /** The class that handles writing to the save file */
    private Storage storage;

    private boolean isExit;

    /**
     * Constructs a new Bibi instance.
     *
     * @param filePath An absolute path to the save file
     */
    public Bibi(String filePath) {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(filePath);
        this.isExit = false;

        // Init
        ui.printWelcomeMessage();
        storage.initializeDataDirectory();
        try {
            storage.restoreTasks(tasks);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        ui.printHorizontalLine();
    }

    /**
     * Runs the chat bot.
     */
    public void run() {
        while (!isExit) {
            Command cmd = Parser.parseCommand(ui.readInput());
            cmd.execute(tasks, ui, storage);
            isExit = cmd.isExit();
        }
    }
    
    public static void main(String[] args) {
        // Insert path to saveFile
        new Bibi("data/list.txt").run();
    }
}
