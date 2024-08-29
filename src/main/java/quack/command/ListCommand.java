package quack.command;

import quack.TaskList;
import quack.Ui;

/**
 * This class is responsible for handling the list command in Quack.
 */
public class ListCommand extends Command{
    
    private TaskList taskList;
    private Ui ui;
    /**
     * Creates a ListCommand object.
     * @param taskList A list that stores all the tasks tracked by Quack.
     * @param ui The ui object that handles user interface requests.
     */
    public ListCommand(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    @Override
    public void execute() {
        ui.printObject(taskList);
    }
}
