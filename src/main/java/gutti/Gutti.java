package gutti;

/**
 * The main class for the Gutti application.
 * Initializes the necessary components and manages the overall execution of the application.
 */
public class Gutti {

    private static final String FILE_PATH = "./data/gutti.txt";
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a new {@code Gutti} instance and initializes the storage, UI, and task list.
     */
    public Gutti() {
        storage = new Storage(FILE_PATH);
        ui = new Ui();
        tasks = new TaskList();
        storage.loadTasksFromFile(tasks);
    }


    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (GuttiException e) {
            return ui.generateError(e);
        }
    }
}
