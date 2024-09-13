package snowy.common;

import snowy.data.SnowyException;
import snowy.tasklist.Task;
import snowy.tasklist.Todo;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a to-do task to the task list.
     *
     * @return a CommandResult indicating the task has been added
     * @throws SnowyException if there is an error adding the task
     */
    @Override
    public CommandResult execute() throws SnowyException {
        Task todo = new Todo(description);
        String str = taskList.addTask(todo);
        return new CommandResult(str + "\nAdded a to-do to your list of tasks: " + description);
    }
}
