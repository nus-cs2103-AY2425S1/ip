package repsmax;

/**
 * The main class of the Repsmax application.
 * This class initializes the necessary components and manages the flow of the program.
 */
public class Repsmax {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Initializes the Repsmax application.
     *
     * @param filePath The file path where task data is stored.
     */
    public Repsmax(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        tasks = new TaskList();
        try {
            storage.load(tasks);
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    /**
     * Runs the main program loop.
     * This method continuously reads user input, parses commands, and manages tasks until the user exits.
     */
    public void run() {
        ui.showWelcome();
        boolean isRunning = true;

        while (isRunning) {
            String userInput = ui.readCommand();
            if (userInput.equals("bye")) {
                isRunning = false;
            } else {
                parser.parse(userInput, tasks, ui, storage);
            }
        }

        ui.showGoodbye();
        storage.save(tasks);
    }

    /**
     * The main entry point of the Repsmax application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Repsmax("C:/Users/nicla/OneDrive/Desktop/Cs2103/repo/src/data.txt").run();
    }
}

