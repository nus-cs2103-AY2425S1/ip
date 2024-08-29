package monique;

import java.io.FileNotFoundException;
import java.io.IOException;

import monique.command.Command;
import monique.exception.MarkException;
import monique.exception.MoniqueException;
import monique.exception.ParseException;
import monique.exception.UnknownCommandException;
import monique.parser.Parser;
import monique.storage.Storage;
import monique.tasklist.TaskList;
import monique.ui.Ui;

/**
 * The <code>Monique</code> class represents the main class of the Monique application.
 * It initializes the necessary components such as the user interface, storage, and task list,
 * and manages the flow of the application.
 */
public class Monique {
    //Create array to store tasks
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a new <code>Monique</code> object.
     * Initializes the user interface, storage system, and task list
     * from the specified file path.
     *
     * @param filePath The file path for loading the task list data.
     * @throws IOException If an input or output exception occurs during file handling.
     */
    public Monique(String filePath) throws IOException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.loadData());
    }

    /**
     * Starts the main loop of the Monique application.
     * Displays a welcome message and continues to process user commands
     * until the application is no longer active.
     */
    public void run() {
        ui.showWelcome();
        boolean isActive = true;
        ui.showLine();

        while (isActive) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isActive = c.isActive();
            } catch (MoniqueException me) {
                me.advice();
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The entry point of the Monique application.
     * Initializes and runs the Monique application with the specified file path for task storage.
     *
     * @param args Command-line arguments passed to the program (not used).
     * @throws IOException If an input or output exception occurs during file handling.
     * @throws MarkException If there is an issue with marking tasks.
     * @throws ParseException If there is an error in parsing the user commands.
     * @throws UnknownCommandException If an unknown command is encountered.
     * @throws FileNotFoundException If the specified file path for tasks is not found.
     */
    public static void main(String[] args) throws IOException, MarkException, ParseException,
            UnknownCommandException, FileNotFoundException {
        new Monique("data/tasks.txt").run();
    }
}
