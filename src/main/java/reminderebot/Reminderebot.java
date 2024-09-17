package reminderebot;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import reminderebot.commands.Command;
import reminderebot.task.Task;

/**
 * The Reminderebot class represents the main application that interacts with the user.
 * It handles the core functionality of the chatbot, including task management,
 * user input processing, and file storage.
 */
public class Reminderebot {
    // note: needs support for handling multi-line inputs
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    /**
     * Initialise Reminderebot for GUI.
     */
    public Reminderebot() {
        this.ui = new Ui();
        this.storage = new Storage("./data/Reminderebot.txt");
        try {
            this.tasklist = new TaskList(storage.readFileContents());
        } catch (ReminderebotException e) {
            this.tasklist = new TaskList(new ArrayList<Task>()); // in case file not found
            System.out.println(e);
        }
    }

    /**
     * Initialise Reminderebot with filepath.
     * @param filePath
     */
    public Reminderebot(String filePath) {
        assert !(filePath.equals(""));
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasklist = new TaskList(storage.readFileContents());
        } catch (ReminderebotException e) {
            this.tasklist = new TaskList(new ArrayList<Task>()); // in case file not found
            System.out.println(e);
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                // @@author Jason C
                // Reused from https://stackoverflow.com/questions/21974415/
                // how-to-close-this-javafx-application-after-showing-a-message-in-a-text-area-elem
                new Timer().schedule(new TimerTask() {
                    public void run() {
                        shutdown();
                    }
                }, 2500);
            }
            return c.execute(tasklist, ui, storage);
        } catch (ReminderebotException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Shows the welcome message for Remminderebot.
     * @return welcome message
     */
    public String start() {
        return ui.showWelcome();
    }

    /**
     * Clear resources and Shut down Reminderebot
     */
    public void shutdown() {
        System.exit(0);
    }
}
