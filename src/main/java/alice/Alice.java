package alice;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class for the Alice application.
 * Initializes components, loads tasks from storage, and handles user input.
 */
public class Alice {
    private static final String path = "./data/alice.txt";
    private TaskList list;
    private final Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs an Alice application instance with a specified storage path.
     * Initializes the storage, task list, user interface, and parser.
     * Loads existing tasks from storage and adds them to the task list.
     *
     * @param path The file path to the storage location for tasks.
     */
    public Alice(String path) {
        this.storage = new Storage(path);
        this.list = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser(ui, list, storage);
        ArrayList<Task> loadedTasks = storage.load();
        for (Task task : loadedTasks) {
            list.addToList(task);
        }
    }

    /**
     * The entry point of the Alice application.
     * Initializes the application, greets the user, and processes user input in a loop.
     * Exits when the user specifies to stop.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Alice alice = new Alice(path);
        Scanner scanner = new Scanner(System.in);
        alice.ui.greet();
        boolean isOnline = true;
        while (isOnline) {
            isOnline = alice.parser.performAction(scanner.nextLine().trim());
        }
        scanner.close();
    }
}
