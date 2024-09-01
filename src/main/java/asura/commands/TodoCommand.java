package asura.commands;

import asura.data.exception.AsuraException;
import asura.data.tasks.TaskList;
import asura.data.tasks.Todo;
import asura.storage.Storage;
import asura.ui.Ui;

/**
 * Represents a user inputting a TodoCommand
 */
public class TodoCommand extends Command {
    String description;

    /**
     * Creates a TodoCommand with the specified description
     * @param description The description of the task
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a todo task into the task list and outputs feedback to the user
     * @param tasklist The list of tasks of the user
     * @param ui The UI object to give user feedback
     * @param storage The storage object to save/load tasks
     * @throws AsuraException If saving user tasks fails
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws AsuraException {
        Todo newTodo = new Todo(description);
        tasklist.addTask(newTodo);
        storage.save(tasklist.getTaskList());
        output.append("Got it. I've added this todo:\n").append(newTodo).append("\n").append("Now you have ").append(tasklist.size()).append(" tasks in your list.\n");
        ui.printString(output.toString());
    }

    /**
     * Indicates that the user does not want to terminate the program
     * @return A boolean representing whether the program is to be terminated, in this case false
     */
    public boolean isExit() {
        return false;
    }
}
