package diego;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The main class for the Diego task manager application.
 */
public class Diego {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a new Diego application.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Diego(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        storage.createFileIfNotExists();

        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Diego application, accepting user input in a loop.
     */
    public void run() {
        ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            try {
                Command command = parser.parseCommand(input);
                command.execute(tasks, ui, storage);
            } catch (DiegoException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main method that starts the Diego application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Diego diego = new Diego("data/Diego.txt");
        diego.run();
    }
}
