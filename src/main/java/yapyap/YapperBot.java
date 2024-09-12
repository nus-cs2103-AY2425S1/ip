package yapyap;

/**
 * Main class for the YapperBot application. This class initializes the necessary components
 * and provides methods to process user inputs and generate appropriate responses.
 */
public class YapperBot {
    private static final String FILE_PATH = "./data/savedFile.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a YapperBot instance, initializing the user interface, storage, parser,
     * and task list. Loads tasks from the specified file path if available.
     * If loading fails, it starts with an empty task list.
     */
    public YapperBot() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        parser = new Parser();

        try {
            tasks = new TaskList(storage.loadTasks());
            assert tasks.size() >= 0 : "Task list size should be non-negative";
        } catch (YapperBotException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }

        assert ui != null : "UI should be initialized";
        assert storage != null : "Storage should be initialized";
        assert parser != null : "Parser should be initialized";
    }

    /**
     * Processes the user input and returns the appropriate response as a string.
     *
     * @param input The user input.
     * @return The response string generated based on the input command.
     */
    public String getResponse(String input) {
        assert input != null && !input.trim().isEmpty() : "Input should not be null or empty";
        return parser.executeCommandAndGetResponse(input, tasks, ui, storage);
    }
}
