package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.TaskList;
import bottle.task.Todo;

/**
 * Represents a command to add a ToDo task.
 */
public class AddTodoTask extends Command {
    /**
     * The description of the task.
     */
    private final String description;

    /**
     * Instantiates a new AddTodoTask.
     *
     * @param description the description of the ToDo task
     */
    public AddTodoTask(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add the ToDo task to the task list.
     *
     * @param taskList the task list to which the task will be added
     * @param ui       the user interface for displaying messages
     * @param storage   the storage for saving tasks
     * @return a message indicating the task has been added
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert !description.isEmpty() : "Task description cannot be empty!";

        taskList.addTask(new Todo(description));
        storage.saveTasks(taskList.getTaskList());

        return ui.printTaskAddedMessage(taskList.getTaskList());
    }
}
