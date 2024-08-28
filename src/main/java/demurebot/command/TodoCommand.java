package demurebot.command;

import demurebot.task.TaskList;
import demurebot.task.Todo;
import demurebot.ui.Ui;

/**
 * Represents a command to add a todo task to the task list.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a TodoCommand object.
     *
     * @param description Description of the todo task.
     */
    public TodoCommand(String description) {
        super(false);
        this.description = description;
    }

    /**
     * Adds a todo task to the task list.
     *
     * @param list Task list to add task to.
     * @param ui Ui to display the added task.
     */
    @Override
    public void execute(TaskList list, Ui ui) {
        Todo todo = new Todo(description, false);
        list.addTask(todo);
        ui.displayAddTask(todo, list.getSize());
    }
}
