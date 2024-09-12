package duck.commands;

import java.time.format.DateTimeParseException;

import duck.Parser;
import duck.TaskList;
import duck.exceptions.TodoUsageException;
import duck.tasks.DateAndTime;
import duck.tasks.Task;
import duck.tasks.Todo;

public class TodoCommand extends TaskCommand {
    private static final String[] ARGS = {};

    public TodoCommand(TaskList taskList, Parser lineBuffer) {
        super(taskList, lineBuffer, ARGS);
    }

    @Override
    public String createNewTask(TaskList taskList) throws TodoUsageException, DateTimeParseException {
        DateAndTime[] dateAndTimes = parseDateTime();
        if (dateAndTimes.length != 0 || parts.size() < 1) {
            throw new TodoUsageException();
        }
        Task task = new Todo(parts.get(0));
        String response = handleNewTask(task);
        return response;
    }
}