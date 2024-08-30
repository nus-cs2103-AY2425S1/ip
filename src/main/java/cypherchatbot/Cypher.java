package cypherchatbot;
import cypherchatbot.command.Command;
import cypherchatbot.util.CommandReader;
import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

import java.io.FileNotFoundException;

/**
 *  Represents the entry point for the Cypher ChatBot Application.
 *  This class is responsible for initialising the various components
 *  and running the main loop to receive the user inputs
 *
 * @author LinkesV
 */
public class Cypher {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     *  Initializes the Ui, Storage  and TaskList required for the functionality
     *  of the Cypher ChatBot Application
     *
     * @param filePath filepath for the location on the hard disk to store the tasks created
     */
    public Cypher(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError(filePath);
            tasks = new TaskList();
        }
    }

    /**
     *  Main event loop of the Cypher Chat Bot Application.
     *  Event loops runs until the last command given has an exit status of true
     */

    public void run() {
        ui.greet();
        boolean shouldEnd = false;
        while (!shouldEnd) {
            try {
                String fullCommand = ui.readCommand();
                Command c = CommandReader.parse(fullCommand);
                c.execute(tasks,ui,storage);
                shouldEnd = c.isExit();
            } catch (CypherException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     *  Initializes the Cypher class with the preferred file path. Executes the run
     *  method in order to start up the Cypher Chat Bot Application
     */

    public static void main(String[] args) {
        new Cypher("./data/tasks.txt").run();
    }
}

