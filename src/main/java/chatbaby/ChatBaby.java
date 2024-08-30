package chatbaby;

import java.io.File;
import java.util.Locale;

/**
 * Main class for the ChatBaby chatbot application.
 * Handles the initialization of the application, command execution loop,
 * and saving/loading of tasks.
 */
public class ChatBaby {
    private static final String FILE_PATH = "." + File.separator +
            "data" + File.separator + "chatBaby.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a ChatBaby instance and initializes the storage, UI, and task list.
     * Loads tasks from the storage file, or creates a new empty task list if the file cannot be loaded.
     */
    public ChatBaby() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChatBabyException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main execution loop of the ChatBaby chatbot.
     * Continues processing user commands until the exit command is received.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String curCommand = ui.readCommand();
                ui.printLine();
                Command c = Parser.parse(curCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ChatBabyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
        try {
            storage.save(tasks);
        } catch (ChatBabyException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Main method to run the ChatBaby application.
     * Sets the default locale to English to avoid issues with date and time formatting in other languages.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        new ChatBaby().run();
    }
}
