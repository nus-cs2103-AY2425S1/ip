package duke;

import java.util.Scanner;

/**
 * The main class for the MentalHealth task management application.
 * It initializes the required components and handles the main application logic.
 */
public class MentalHealth {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a {@code MentalHealth} object with the specified file path for data storage.
     *
     * @param filePath The file path where the task data is stored.
     */
    public MentalHealth(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (MentalHealthException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * The main method that starts the MentalHealth application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new MentalHealth("./data/duke.txt").run();
    }

    /**
     * Runs the main loop of the MentalHealth application.
     * It repeatedly reads user input,
     * parses it, and
     * executes the corresponding command until an exit command is given.
     */
    public void run() {
        ui.greeting();
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(scanner);
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (MentalHealthException e) {
                ui.formatMessage(e.getMessage());
            }
        }
        scanner.close();
        ui.goodbye();
    }
}
