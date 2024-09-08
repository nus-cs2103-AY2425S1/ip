package reminderebot;

import java.util.ArrayList;

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

    // /**
    //  * Entry point to program.
    //  * @param args
    //  */
    // public static void main(String[] args) {
    //     new Reminderebot("./data/Reminderebot.txt").run();
    // }

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
     * Run Reminderebot for text based.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasklist, ui, storage);
                isExit = c.isExit();
            } catch (ReminderebotException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasklist, ui, storage);
        } catch (ReminderebotException e) {
            return ui.showError(e.getMessage());
        }
    }
}
