package darkpool;

import darkpool.command.Command;
import darkpool.gui.Gui;
import darkpool.util.DarkpoolException;
import darkpool.util.Parser;
import darkpool.util.Storage;
import darkpool.util.TaskList;

/**
 * The Darkpool class represents the main logic of the application.
 * It handles the initialization of the storage, task list, and GUI components.
 * It also processes user input and returns appropriate responses.
 */
public class Darkpool {

    private final Storage storage;
    private TaskList taskList;
    private final Gui gui;

    /**
     * Constructs a new Darkpool instance.
     * Initializes the GUI, storage, and task list.
     * If loading data from storage fails, an empty task list is created.
     */
    public Darkpool() {
        gui = new Gui();
        String filePath = "data/tasks.txt";
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (DarkpoolException e) {
            // Show error message if loading data fails
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Processes the user input and returns the response.
     * Parses the input to create a command, executes the command, and returns the response.
     * If the command indicates an exit, returns the goodbye message from the GUI.
     *
     * @param input The user input to be processed.
     * @return The response after processing the input.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(taskList, gui, storage);
            if (command.isExit()) {
                return gui.goodbye();
            }
            return response;
        }  catch (DarkpoolException e) {
            return e.getMessage();
        }
    }

}