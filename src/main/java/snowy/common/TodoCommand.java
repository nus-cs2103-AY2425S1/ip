package snowy.common;

import snowy.data.SnowyException;
import snowy.tasklist.Task;
import snowy.tasklist.Todo;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public CommandResult execute() throws SnowyException {
        Task todo = new Todo(description);
        taskList.addTask(todo);
        return new CommandResult("Added a to-do to your list of tasks: " + description);
    }
}
