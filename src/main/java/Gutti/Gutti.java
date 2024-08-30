package Gutti;

/**
 * The main class for the Gutti application.
 * Initializes the necessary components and manages the overall execution of the application.
 */
public class Gutti {

    private static final String FILE_PATH = "./data/gutti.txt";
    public TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a new {@code Gutti} instance and initializes the storage, UI, and task list.
     */
    public Gutti() {
        storage = new Storage(FILE_PATH);
        ui = new Ui();
        tasks = new TaskList();
    }

    /**
     * The entry point of the program.
     * @param args (not used)
     */
    public static void main(String[] args) {
        new Gutti().run();
    }

    /**
     * Executes the main logic of the application, including reading commands and executing them.
     */
    public void run() {
        boolean isExit = false;
        ui.greetings();
        storage.loadTasksFromFile(tasks);

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (GuttiException e) {
                ui.generateError(e);
            }
        }

        ui.goodBye();
    }
}