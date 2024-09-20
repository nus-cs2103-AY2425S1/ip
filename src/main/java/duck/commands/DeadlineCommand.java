package duck.commands;

import java.time.format.DateTimeParseException;
import java.util.Map;

import duck.Parser;
import duck.TaskList;
import duck.exceptions.DeadlineUsageException;
import duck.exceptions.MissingArgsException;
import duck.tasks.DateAndTime;
import duck.tasks.Deadline;
import duck.tasks.Task;

public class DeadlineCommand extends TaskCommand {
    private static final Map<String, String> ARGS = Map.ofEntries(
            Map.entry("/by", "due_date"));

    public DeadlineCommand(TaskList taskList, Parser lineBuffer) {
        super(taskList, lineBuffer, ARGS);
    }

    @Override
    public String createNewTask(TaskList taskList) throws MissingArgsException, DateTimeParseException {
        verifyArgsArePresent(new DeadlineUsageException());
        String description = parts.get("");
        DateAndTime due_date = new DateAndTime(parts.get("/by"));
        Task task = new Deadline(description, due_date);
        String response = handleNewTask(task);
        return response;
    }
}