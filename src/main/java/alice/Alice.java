package alice;

import java.util.ArrayList;
import java.util.Scanner;

import alice.task.Task;
import alice.utility.Parser;
import alice.utility.Storage;
import alice.utility.TaskList;
import alice.utility.Ui;

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
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructs an Alice application instance with a specified storage path.
     * Initializes the storage, task list, user interface, and parser.
     * Loads existing tasks from storage and adds them to the task list.
     */
    public Alice() {
        this.storage = new Storage(path);
        this.list = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser(ui, list, storage);
        ArrayList<Task> loadedTasks = storage.load();
        for (Task task : loadedTasks) {
            list.addToList(task);
        }
    }

    public String getGreeting() {
        return ui.greetMsg();
    }

    public String getResponse(String input) {
        return parser.performAction(input);
    }

    public Storage getStorage() {
        return storage;
    }

    public TaskList getList() {
        return list;
    }
}
