package quack.command;

import quack.util.TaskList;
import quack.util.Ui;

/**
 * This class is responsible for handling the list command in Quack.
 */
public class ListCommand extends Command {

    /** List to store all tasks by Quack */
    private TaskList taskList;
    /** Ui to handle all user display interactions */
    private Ui ui;

    /**
     * Creates a ListCommand object.
     * @param taskList A list that stores all the tasks tracked by Quack.
     * @param ui The ui object that handles user interface requests.
     */
    public ListCommand(TaskList taskList, Ui ui) {
        super();
        this.taskList = taskList;
        this.ui = ui;
    }

    @Override
    public void prompt() {
        this.execute("");
    }

    @Override
    public void execute(String input) {

        String output = ui.objectToString(taskList);
        ui.outputToScreen(output);
        this.completeCommand();
    }
}
