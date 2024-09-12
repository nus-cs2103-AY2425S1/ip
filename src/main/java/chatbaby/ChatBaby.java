package chatbaby;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Locale;

/**
 * Main class for the ChatBaby chatbot application.
 * Handles the initialization of the application, command execution loop,
 * and saving/loading of tasks.
 */
public class ChatBaby {
    private static final String FILE_PATH = "." + File.separator
            + "data" + File.separator + "chatBaby.txt";
    private static final String GREETING_MESSAGE = "Hello! I'm ChatBaby, "
            + "your task management assistant. What can I do for you?";
    private static final String BYE_MESSAGE = "Bye! Hope to see you soon!";
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
     * Returns greeting message.
     */
    public String greet() {
        return GREETING_MESSAGE;
    }

    /**
     * Returns bye message.
     */
    public String bye() {
        return BYE_MESSAGE;
    }

    /**
     * Returns response to show on the main window.
     */
    public String getResponse(String input) {
        boolean isExit = false;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (ChatBabyException e) {
            ui.showError(e.getMessage());
        }
        try {
            storage.save(tasks);
        } catch (ChatBabyException e) {
            ui.showError(e.getMessage());
        }
        if (isExit) {
            return bye();
        }
        return outputStream.toString();
    }

    /**
     * Main method to run the ChatBaby application.
     * Sets the default locale to English to avoid issues with date and time formatting in other languages.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
    }
}
