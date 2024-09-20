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
        DateAndTime start_date = new DateAndTime(parts.get("/from"));
        DateAndTime end_date = new DateAndTime(parts.get("/to"));
        Task task = new Event(description, start_date, end_date);
        String response = handleNewTask(task);
        return response;
    }
}