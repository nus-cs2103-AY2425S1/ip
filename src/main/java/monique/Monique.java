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
    //This version uses and implements checkstyle
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private String commandType = "default";

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
     * Processes the user input by parsing it into a command, executing the command,
     * and returning an appropriate response. If the command is inactive, a goodbye message
     * is returned. If an exception is thrown during command execution, the advice from the
     * exception is returned.
     *
     * @param input the user input string to be processed
     * @return the response generated based on the input, command execution, or exception advice
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(taskList, ui, storage);
            commandType = c.getCommandType();
            if (!c.isActive()) {
                return ui.showGoodbye();
            }
            return c.getResponse(ui);
        } catch (MoniqueException e) {
            return e.advice();
        }
    }

    /**
     * Retrieves the welcome message to be displayed when the application starts.
     *
     * @return the welcome message from the Ui instance
     */
    public String getWelcomeMessage() {
        return ui.showWelcome();
    }
    /**
     * Retrieves the Ui instance associated with this Monique instance.
     *
     * @return the Ui instance
     */
    public Ui getUi() {
        return this.ui;
    }

    public String getCommandType() {
        return commandType;
    }

}
