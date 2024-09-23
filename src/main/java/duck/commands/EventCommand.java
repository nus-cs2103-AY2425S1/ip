package duck.commands;

import java.time.format.DateTimeParseException;
import java.util.Map;

import duck.Parser;
import duck.TaskList;
import duck.exceptions.EventUsageException;
import duck.exceptions.MissingArgsException;
import duck.tasks.DateAndTime;
import duck.tasks.Event;
import duck.tasks.Task;

/**
 * Class representing the command to create an "event" task.
 */
public class EventCommand extends TaskCommand {
    private static final Map<String, String> ARGS = Map.ofEntries(
            Map.entry("/from", "start_date"),
            Map.entry("/to", "end_date"));

    public EventCommand(TaskList taskList, Parser lineBuffer) {
        super(taskList, lineBuffer, ARGS);
    }

    @Override
    public String createNewTask(TaskList taskList) throws MissingArgsException, DateTimeParseException {
        verifyArgsArePresent(new EventUsageException());
        String description = parts.get("");
        DateAndTime startDate = new DateAndTime(parts.get("/from"));
        DateAndTime endDate = new DateAndTime(parts.get("/to"));
        Task task = new Event(description, startDate, endDate);
        String response = handleNewTask(task);
        return response;
    }
}
