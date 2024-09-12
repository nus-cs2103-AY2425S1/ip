package duck.commands;

import java.time.format.DateTimeParseException;

import duck.Parser;
import duck.TaskList;
import duck.exceptions.EventUsageException;
import duck.tasks.DateAndTime;
import duck.tasks.Event;
import duck.tasks.Task;

public class EventCommand extends TaskCommand {
    private static final String[] ARGS = { "/from", "/to" };

    public EventCommand(TaskList taskList, Parser lineBuffer) {
        super(taskList, lineBuffer, ARGS);
    }

    @Override
    public String createNewTask(TaskList taskList) throws EventUsageException, DateTimeParseException {
        DateAndTime[] dateAndTimes = parseDateTime();
        if (dateAndTimes.length != 2 || parts.size() < 1) {
            throw new EventUsageException();
        }
        Task task = new Event(parts.get(0), dateAndTimes[0], dateAndTimes[1]);
        String response = handleNewTask(task);
        return response;
    }
}