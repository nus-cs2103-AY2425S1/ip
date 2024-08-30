package lolo;
import lolo.command.Command;
import lolo.command.Parser;
import lolo.storage.Storage;
import lolo.task.TaskList;

/**
 * Represents the main class of the Lolo chatbot application.
 * Responsible for initializing the application, handling user commands,
 * and managing the flow of the program.
 */

public class Lolo {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Lolo object with the specified file path.
     * Initializes the UI, storage, and task list. If the task list
     * cannot be loaded from the specified file, a new empty task list is created.
     *
     * @param filePath The file path to load tasks from.
     */
    public Lolo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LoloException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Lolo chatbot application.
     * Continuously reads and executes user commands until the exit command is given.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (LoloException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main entry point of the Lolo application.
     * Creates a new Lolo object and starts the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Lolo("./data/lolo.txt").run();
    }
}


