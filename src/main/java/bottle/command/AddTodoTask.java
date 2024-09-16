package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.TaskList;
import bottle.task.Todo;

/**
 * The type Add todo task.
 */
public class AddTodoTask extends Command {
    /**
     * The Description.
     */
    private final String description;

    /**
     * Instantiates a new Add todo task.
     *
     * @param description the description
     */
    public AddTodoTask(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert !description.isEmpty() : "Task description cannot be empty!";
        taskList.addTask(new Todo(description));
        storage.saveTasks(taskList.getTaskList());
        return ui.printTaskAddedMessage(taskList.getTaskList());

    }
}
