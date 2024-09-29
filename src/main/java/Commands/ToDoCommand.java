package Commands;

import task.TaskList;
import task.ToDoTask;
import ui.Ui;

/**
 * Represents a command to create a ToDo task.
 * This command extends from the GeneralTaskCommand and adds a todo task
 * to the main task list.
 * Once done so, it returns a message from the Ui class indicating that the
 * task has been added.
 */
public class ToDoCommand extends GeneralTaskCommand {

    public ToDoCommand(String c) {
        super(c);
    }

    /**
     * Executes the action of creating a new To Do task and adds it to the task list
     * Returns a message indicating that the task has been added successfully
     * @return String message confirming that the To Do task has been added
     */
    @Override
    public String commandAction() {
        ToDoTask tsk = new ToDoTask(this.cmd);
        TaskList.addTask(tsk);
        return Ui.taskAddDescription(tsk);
    }
}
