package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.task.Task;
import morgana.task.Todo;

public class TodoCommand extends AddCommand {
    public TodoCommand(String args) {
        super(args);
    }

    @Override
    Task createTask(String description) throws MorganaException {
        if (description.isEmpty()) {
            throw new MorganaException("Please specify a description for your todo.");
        }
        return new Todo(description);
    }
}
