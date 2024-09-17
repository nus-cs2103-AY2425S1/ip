package strand;

import java.util.Scanner;

import strand.command.Command;
import strand.exception.StrandException;

/**
 * The {@code Strand} class represents the main entry point for the Strand application.
 */
public class Strand {
    private static final String FILENAME = "./data/strand.txt";
    private final Storage storage;
    private final Ui ui;
    private TaskList listOfTasks;

    /**
     * Constructs a new {@code Strand} instance with the specified file path for storage.
     *
     * @param filePath The file path to load tasks from.
     */
    public Strand(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            listOfTasks = new TaskList(storage.load());
        } catch (StrandException e) {
            ui.showLoadingError();
            listOfTasks = new TaskList();
        }
    }

    /**
     * Starts the Strand application by creating an instance of the {@code Strand}
     * class and running the application loop.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Strand(FILENAME).run();
    }

    /**
     * Starts the main application loop.
     */
    public void run() {
        ui.welcome();
        boolean isRunning = true;
        Scanner scan = new Scanner(System.in);
        while (isRunning) {
            String userInput = scan.nextLine();
            assert userInput != null : "User input should not be null";
            try {
                ui.showLine();
                Command c = Parser.parse(userInput);
                assert c != null : "Parsed command should not be null";
                c.execute(listOfTasks, ui, storage);
                isRunning = c.isRunning();
            } catch (StrandException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        scan.close();
    }

    public String getResponse(String input) {
        assert input != null : "Input for getResponse should not be null";
        try {
            Command c = Parser.parse(input);
            assert c != null : "Parsed command in getResponse should not be null";
            return c.execute(listOfTasks, ui, storage);
        } catch (StrandException e) {
            return ui.showError(e.getMessage());
        }
    }

    public String getWelcomeMessage() {
        return ui.welcome();
    }
}
