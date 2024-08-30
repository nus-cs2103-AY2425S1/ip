package yapyap;

/**
 * Main class for the YapperBot application. This class initialises the necessary components
 * and runs the application, handling user input and executing commands.
 */
public class YapperBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private static final String FILE_PATH = "./data/savedFile.txt";

    /**
     * Creates a YapperBot instance, initializing the user interface, storage, parser,
     * and task list. Loads tasks from the specified file path if available.
     * If loading fails, starts with an empty task list.
     */
    public YapperBot() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        parser = new Parser();

        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (YapperBotException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the YapperBot application, displaying the start message and entering
     * a loop to continuously read and execute user commands until the "bye" command is given.
     */
    public void run() {
        ui.printStartMessage();

        boolean isRunning = true;
        while (isRunning) {
            String userInput = ui.readCommand(); // scanner.nextLine()
            Commands userCommand = parser.parseCommand(userInput); // return command variable
            isRunning = executeCommand(userCommand, userInput);
        }
    }

    /**
     * Executes the given command based on user input.
     *
     * @param command The command to execute.
     * @param userInput The raw input from the user.
     * @return A boolean indicating whether the application should continue running.
     */
    private boolean executeCommand(Commands command, String userInput) {
        switch (command) {
        case BYE:
            storage.saveTasks(tasks.getTasks());
            ui.printEndMessage();
            return false; // terminates loop
        case LIST:
            ui.showTaskList(tasks);
            break;
        case MARK:
            parser.markCommand(tasks, userInput, storage);
            break;
        case UNMARK:
            parser.unmarkCommand(tasks, userInput, storage);
            break;
        case TODO:
            parser.todoCommand(tasks, userInput, storage);
            break;
        case DEADLINE:
            parser.deadlineCommand(tasks, userInput, storage);
            break;
        case EVENT:
            parser.eventCommand(tasks, userInput, storage);
            break;
        case DELETE:
            parser.deleteCommand(tasks, userInput, storage);
            break;
        case FIND:
            parser.findCommand(tasks, userInput, ui);
            break;
        default:
            ui.printInvalidCommandMessage();
        }
        return true;
    }

    /**
     * The main entry point of the application. Creates a new YapperBot instance and runs it.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new YapperBot().run();
    }
}
