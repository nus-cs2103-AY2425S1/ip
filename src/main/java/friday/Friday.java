package friday;

import commands.Command;
import parser.Parser;
import storage.Storage;
import storage.TaskList;
import ui.UI;

/**
 * The main class that runs the Friday application, a to-do list chatbot.
 * The Friday class initializes the necessary components (Storage, TaskList, and UI)
 * and manages the application's main loop, handling user input and executing commands.
 */
public class Friday {

    private final Storage storage;
    private final TaskList tasks;
    private final UI ui;

    /**
     * Constructs a Friday object with the specified file path for storage.
     * Initializes the Storage, TaskList, and UI components.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public Friday(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.initList());
        assert this.tasks.getSize() >= 0 : "Invalid list size";
        this.ui = new UI();
    }

    /**
     * Runs the main loop of the Friday application.
     * This method initializes the UI, processes user input, and executes commands
     * until the user issues a command to exit the application.
     * Upon termination, it saves the task list to the storage file.
     */
    public void run() {
        System.out.println(this.ui.init());
        boolean isByeCommand = false;

        while (!isByeCommand) {
            System.out.print("Your input > ");
            String[] parsed = this.ui.getInput();
            Command command = Parser.parse(parsed);
            String response = command.execute(this.storage, this.tasks);
            System.out.println(response);
            if (parsed[0].equalsIgnoreCase("bye")) {
                isByeCommand = true;
            }
        }
        this.storage.saveList(this.tasks.getTasks());
    }

    /**
     * Runs the main entry point of the Friday application.
     * Creates a new Friday instance with the specified file path and runs it.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Friday("./data/friday.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String[] parsed = input.split(" ", 2);
        if (parsed[0].equalsIgnoreCase("bye")) {
            System.exit(0);
        }
        Command command = Parser.parse(parsed);
        return command.execute(this.storage, this.tasks);
    }
}
