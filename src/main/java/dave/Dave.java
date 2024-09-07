package dave;

import java.io.IOException;

import dave.command.Command;
import dave.exceptions.InvalidCommandException;
import dave.parser.Parser;
import dave.storage.Storage;
import dave.task.TaskList;
import dave.ui.Ui;


/**
 * The main class for the Dave application, responsible for initializing components
 * and running the main program loop.
 */
public class Dave {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Dave instance, initializing the user interface, task list, and storage components.
     *
     * @param filePath The file path where tasks will be stored and loaded from.
     */
    public Dave(String filePath) {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(filePath, this.tasks);
    }

    /**
     * Runs the main program loop, accepting user commands and executing them until the user exits.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);

                if (c == null) {
                    continue;
                }

                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (InvalidCommandException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showLine();
                System.out.println("An error occurred while trying to write to the file: " + e.getMessage());
                e.printStackTrace();
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main entry point of the Dave application. It initializes the Dave instance
     * with a specific file path and starts the program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Dave("C:\\Users\\thamy\\OneDrive\\data\\daveData.txt").run();
    }
}
