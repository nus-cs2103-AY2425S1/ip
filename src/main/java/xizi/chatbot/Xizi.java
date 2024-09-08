package xizi.chatbot;
//https://nus-cs2103-ay2425s1.github.io/website/admin/standardsAndConventions.html

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import javafx.application.Platform;
import xizi.chatbot.command.Command;
import xizi.chatbot.task.Task;
import xizi.chatbot.task.TaskList;

/**
 * The main class for the Xizi chatbot application. This class handles the initialization and
 * running of the chatbot, including loading tasks from a file, parsing user input, and
 * executing commands.
 */
public class Xizi {
    private static final String FILE_PATH = "data/xizi.txt";
    private final Storage storage;
    private TaskList actions;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructs a Xizi object with the specified file path for task storage.
     * Initializes the UI, storage, task list, and parser.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Xizi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        actions = new TaskList();
        parser = new Parser();

        try {
            List<Task> loadedTasks = storage.load();
            actions = new TaskList(loadedTasks);
        } catch (IOException | XiziException e) {
            ui.printErrorMessage(e.getMessage());
        }
        assert actions != null : "Loaded tasks should not be null.";
    }

    /**
     * The main entry point for the Xizi chatbot application.
     * Creates a new instance of Xizi with the default file path and starts the chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Xizi xizi = new Xizi(FILE_PATH);
        xizi.run();
    }

    /**
     * Runs the Xizi chatbot. Displays the welcome message, then continuously reads
     * user input, parses it into commands, and executes those commands until the
     * user inputs "bye" to exit the application.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = ui.readUserInput();
                Command command = parser.parse(userInput);
                command.execute(actions, storage, ui);
                isRunning = !userInput.equals("bye");
            } catch (XiziException | IOException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    public Ui getUi() {
        return ui;
    }

    public Parser getParser() {
        return parser;
    }

    public TaskList getActions() {
        return actions;
    }

    public Storage getStorage() {
        return storage;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            // Print the absolute path of the file for debugging
            //Path filePath = Paths.get(FILE_PATH);
            //System.out.println("Absolute path: " + filePath.toAbsolutePath());

            Command command = parser.parse(input);
            assert command != null : "Parsed command should not be null.";

            // Redirect output to a ByteArrayOutputStream to capture the response
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(byteStream);
            ui.setOutput(ps);

            // Execute the command
            command.execute(actions, storage, ui);
            ps.flush(); // Ensure all data is written to the ByteArrayOutputStream
            return byteStream.toString().trim();
        } catch (XiziException | IOException e) {
            return e.getMessage(); // Return the error message as the response
        }
    }
    /**
     * Returns the welcomes message as a string to the GUI.
     */
    public String returnWelcomeString() {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteStream);
        ui.setOutput(ps);
        ui.showWelcomeMessage();
        ps.flush();
        return byteStream.toString().trim();

    }

}
