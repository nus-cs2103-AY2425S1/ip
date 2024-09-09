package ned;

import java.nio.file.Paths;
import java.util.ArrayList;

import ned.commands.Command;
import ned.exceptions.NedException;

/**
 * Represents the chatbot, Ned, which will read and react to user commands.
 */
public class Ned {
    public static final String CACHED_TASKS_PATH = Paths.get("src", "data", "cachedTasks.txt").toString();
    private static final String EXIT_MESSAGE = "EXIT MESSAGE";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;


    /**
     * Creates an instance of the Ned chatbot.
     *
     * @param filePath The address of the cache file, relative to the project folder
     */
    public Ned(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(this.storage.load());
        } catch (NedException e) {
            ui.showLoadingError();
            this.tasks = new TaskList(new ArrayList<>());
        }
    }

    public static void main(String[] args) {
        new Ned(Ned.CACHED_TASKS_PATH).run();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage, input);
            if (c.isExit()) {
                return EXIT_MESSAGE;
            }
            return ui.getAllBuiltUpDialogue();
        } catch (NedException e) {
            return e.getMessage();
        }
    }

    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    public String getByeMessage() {
        return ui.getByeMessage();
    }

    /**
     * Shows the welcome message and then sends input to be parsed
     * Will exit if the command has a isExit = true
     */
    public void run() {
        ui.getWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage, fullCommand);
                isExit = c.isExit();
            } catch (NedException e) {
                ui.addToNedDialogue(e.getMessage());
            }
        }
    }
}
