package bob;

import java.util.ArrayList;

import bob.command.Command;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * This is a chatBot class named Bob.
 */
public class Bob {
    private static final String FILE_PATH = "dataForBob/tasks.txt";
    private final Storage storage;
    private TaskList taskList;
    private Ui ui;
    private String commandType;
    /**
     * Initialises an instance of the chatBot Bob.
     * Bob's ui, storage and taskList is also initialised.
     *
     * @param filePath Relative file path of where the records will be saved.
     */
    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        ArrayList<Task> records = storage.loadTaskList();
        if (records == null) {
            taskList = new TaskList();
        } else {
            taskList = new TaskList(records); //records are loaded
        }
    }

    /**
     * Runs the main method of the program.
     */
    public static void main(String[] args) {
        Bob bob = new Bob(FILE_PATH);
        bob.run1();
    }

    /**
     * Runs the main program.
     */
    public void run1() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            taskList = storage.loadUpdatedTaskList(); //Updates taskList based on previous input.
            String fullCommand = ui.readCommand();
            ui.showLine();
            Command c = Parser.parseCommand(fullCommand);
            c.execute(taskList, storage, ui);
            isExit = c.isExit();
            ui.showLine();
        }
        Ui.showGoodBye();
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input
     */
    public String getResponse(String input) {
        taskList = storage.loadUpdatedTaskList(); //Updates taskList based on previous input.
        Command c = Parser.parseCommand(input);
        String output = c.execute(taskList, storage, ui);
        commandType = c.getClass().getSimpleName();
        return output;
    }

    /**
     * Returns the command type.
     */
    public String getCommandType() {
        return commandType;
    }
}
