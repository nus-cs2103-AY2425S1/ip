package Bwead;

import java.io.IOException;

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
        ui.set(history, tasks);
        assert history != null;
        assert tasks != null;
    }
    /*
    /**
     * Starts the chatbot by calling ui.handleCommands(input) to handle the command inputs.
     *
     * @throws IOException when a failure occurs while performing read or write operations
     *     from the handleCommands() method.
     *
    public void run() throws IOException {
        System.out.println("Hello from " + name + "!");
    }*/

    public String getResponse(String input) {
        try {
            return ui.handleCommands(input);
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /*
    /**
     * Main method of Bwead.
     *
     * @param args
     * @throws IOException when a failure occurs while performing read or write operations
     *     from the run() method.
     *
    public static void main(String[] args) throws IOException {
        new Bwead("./src/main/java/Bwead/historyFile.txt").run();
    }*/
}

