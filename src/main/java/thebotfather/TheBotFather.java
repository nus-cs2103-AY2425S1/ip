package thebotfather;

import java.util.ArrayList;

import thebotfather.command.Command;
import thebotfather.parser.Parser;
import thebotfather.util.Storage;
import thebotfather.util.TaskList;
import thebotfather.util.TheBotFatherException;
import thebotfather.util.Ui;

/**
 * The main class for TheBotFather application.
 * TheBotFather is a task management application that allows users to manage their tasks
 * through a GUI interface.
 * <p>
 * The class initializes the necessary components for the application, such as
 * storage, task list, and user interface, and runs the main loop of the program.
 */
public class TheBotFather {

    /**
     * The storage instance used to read from and write to the data file.
     */
    private final Storage storage;

    /**
     * The task list containing the current tasks.
     */
    private TaskList taskList;

    /**
     * The user interface instance used to interact with the user.
     */
    private final Ui ui;

    /**
     * Constructs a new TheBotFather instance.
     * Initializes the user interface, task list, and storage components.
     * It attempts to load any existing tasks from the provided file path.
     * If there's an error loading the file, a loading error message is shown.
     *
     * @param filePath The file path where the task data is stored.
     */
    public TheBotFather(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(new ArrayList<>());
        try {
            taskList = storage.load();
        } catch (TheBotFatherException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Processes the user's input and returns the response from TheBotFather.
     * It parses the input into a command, executes it, and returns the response.
     * If the command is an exit command, it returns a special "EXIT-CODE".
     *
     * @param input The user's input.
     * @return The response from TheBotFather, or "EXIT-CODE" if the input is an exit command.
     * @throws TheBotFatherException If there is an error while processing the command.
     */
    public String getResponse(String input) throws TheBotFatherException {
        Command command = Parser.parse(input, ui);
        String response = command.execute(taskList, ui, storage);
        if (command.isExit()) {
            return "EXIT-CODE";
        }
        return response;
    }

    /**
     * Returns the greeting message to be displayed when the application starts.
     *
     * @return A string containing the greeting message.
     */
    public String getGreeting() {
        return ui.getGreeting();
    }
}
