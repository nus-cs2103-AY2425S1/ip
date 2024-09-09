package king.commands;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.ui.Ui;
import king.task.Todo;

/**
 * Represents a command that adds a new to-do task to the task list.
 * This command creates a new to-do task with the provided description and adds it to the task list.
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * Creates a TodoCommand with the specified task description.
     *
     * @param description The description of the to-do task to be added.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the to-do command by creating a new to-do task and adding it to the task list.
     *
     * @param tasks The task list where the new to-do task will be added.
     * @param ui The user interface used to show the status of the new task.
     * @param storage The storage used to save the updated task list.
     * @throws KingException If there is an issue saving the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KingException {
        Todo todo = new Todo(description);
        tasks.add(todo);
        storage.save(tasks.getTaskList());
        return ui.showTaskAdded(todo, tasks.size());
    }

    /**
     * Indicates whether this command signals the application to exit.
     *
     * @return {@code false} as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
