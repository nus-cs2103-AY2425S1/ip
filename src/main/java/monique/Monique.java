package monique;

import java.io.IOException;

import monique.command.Command;
import monique.exception.MoniqueException;
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

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(taskList, ui, storage);
            if (!c.isActive()) {
                return ui.showGoodbye();
            }
            return c.getResponse(ui);
        } catch (MoniqueException e) {
            return e.advice();
        }
    }


    public String getWelcomeMessage() {
        return ui.showWelcome();
    }

    public Ui getUi() {
        return this.ui;
    }


}
