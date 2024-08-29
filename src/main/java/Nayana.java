/**
 * Nayana is a class that demonstrates a simple console-based interaction.
 * It manages user interaction, command processing, and task management.
 */
public class Nayana {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Nayana instance with the specified file path for task storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Nayana(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NayanaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the application, processing user commands until exit is requested.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NayanaException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method is the entry point of the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        String filePath = "data/nayana.txt";
        new Nayana(filePath).run();
    }
}
