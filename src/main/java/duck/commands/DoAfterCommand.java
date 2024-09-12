package duck.commands;

import java.time.format.DateTimeParseException;

import duck.Parser;
import duck.TaskList;
import duck.exceptions.DeadlineUsageException;
import duck.tasks.DateAndTime;
import duck.tasks.DoAfter;
import duck.tasks.Task;

public class DoAfterCommand extends TaskCommand {
    private static final String[] ARGS = { "/after" };

    public DoAfterCommand(TaskList taskList, Parser lineBuffer) {
        super(taskList, lineBuffer, ARGS);
    }

    @Override
    public String createNewTask(TaskList taskList) throws DeadlineUsageException, DateTimeParseException {
        DateAndTime[] dateAndTimes = parseDateTime();
        if (dateAndTimes.length != 1 || parts.size() < 1) {
            throw new DeadlineUsageException();
        }
        Task task = new DoAfter(parts.get(0), dateAndTimes[0]);
        String response = handleNewTask(task);
        return response;
    }
}