package Bwead;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a chatbot. the chatbot takes in commands by users
 * and records, marks, and deletes tasks.
 */
public class Bwead {

    private static String name = "Bwead";
    private History history;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Bwead instance using a file path which points to local file.
     * a new ui, and a history is created with the file path.
     * a taskList will be instantiated with tasks from the saved file, if any. else, an empty one will be created.
     * if load() throws BweadException due to file not existing, the taskList will be empty.
     *
     * @param filePath file path of the local file which will save tasks.
     */
    public Bwead(String filePath) {
        ui = new Ui();
        history = new History(filePath);
        try {
            tasks = new TaskList(history.load());
        } catch (Exception e) {
            ui.showLoadingError(e.getMessage());
        }
        if (tasks == null) {
            ArrayList<Task> arrayList = new ArrayList<Task>();
            tasks = new TaskList(arrayList);
        }
        ui.set(history, tasks);
        //assert history != null;
        //assert tasks != null;
    }

    /**
     * Gets the response according to the input given by user.
     *
     * @param input from user.
     * @return String to reply user.
     */
    public String getResponse(String input) {
        try {
            return ui.handleCommands(input);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}

