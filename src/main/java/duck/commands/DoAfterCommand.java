package duck.commands;

import java.time.format.DateTimeParseException;
import java.util.Map;

import duck.Parser;
import duck.TaskList;
import duck.exceptions.DoAfterUsageException;
import duck.exceptions.MissingArgsException;
import duck.tasks.DateAndTime;
import duck.tasks.DoAfter;
import duck.tasks.Task;

public class DoAfterCommand extends TaskCommand {
    private static final Map<String, String> ARGS = Map.ofEntries(
            Map.entry("/after", "earliest_date"));

    public DoAfterCommand(TaskList taskList, Parser lineBuffer) {
        super(taskList, lineBuffer, ARGS);
    }

    @Override
    public String createNewTask(TaskList taskList) throws MissingArgsException, DateTimeParseException {
        verifyArgsArePresent(new DoAfterUsageException());
        String description = parts.get("");
        DateAndTime earliest_date = new DateAndTime(parts.get("/after"));
        Task task = new DoAfter(description, earliest_date);
        String response = handleNewTask(task);
        return response;
    }
}