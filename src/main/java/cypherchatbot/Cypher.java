package cypherchatbot;

import java.io.FileNotFoundException;

import cypherchatbot.command.Command;
import cypherchatbot.controller.MainWindow;
import cypherchatbot.util.CommandReader;
import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;



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
    private MainWindow mainWindow;

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
            tasks = new TaskList();
            this.sendDialog(ui.showLoadingError(filePath));
        }
    }


    /**
     *  Main event loop of the Cypher Chat Bot Application.
     *  Event loops runs until the last command given has an exit status of true
     */



    /**
     *  Initializes the Cypher class with the preferred file path. Executes the run
     *  method in order to start up the Cypher Chat Bot Application
     */

    public static void main(String[] args) {
        // No More CLI function
    }

    public void getResponse(String fullCommand) {
        try {
            Command c = CommandReader.parse(fullCommand);
            this.sendDialog(c.execute(tasks, ui, storage));
        } catch (CypherException e) {
            this.sendDialog(ui.showError(e.getMessage()));
        }
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    private void sendDialog(String response) {
        // assert this.mainWindow != null : "Main Window does not exist for cypher";
        this.mainWindow.addDialogFromCypher(response);
    }
}

