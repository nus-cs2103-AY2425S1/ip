package nugget;

import java.util.Scanner;

/**
 * The main class for the Nugget application, which handles task management.
 */
public class Nugget {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructs a Nugget instance with the specified file path for storage.
     *
     * @param filePath The file path where tasks will be loaded and saved.
     */
    public Nugget(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
        parser = new Parser(tasks);
    }

    /**
     * Starts the Nugget application, accepting user input and executing commands
     * until the user exits.
     */
    public void run() {
        ui.printIntro();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine().trim();
            try {
                if (command.equals("bye")) {
                    break;
                }
                Command c = parser.parse(command);
                c.execute(tasks, ui, storage);
            } catch (NuggetException e) {
                ui.showError(e.getMessage());
            }
        }

        ui.printEnd();
    }

    /**
     * The main entry point for the Nugget application.
     *
     * @param args Command line arguments; not used in this application.
     */
    public static void main(String[] args) {
        new Nugget("data/nugget.txt").run();
    }
}
