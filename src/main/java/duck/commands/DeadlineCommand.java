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

/**
 * Class representing the command to create a "deadline" task.
 */
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
        DateAndTime dueDate = new DateAndTime(parts.get("/by"));
        Task task = new Deadline(description, dueDate);
        String response = handleNewTask(task);
        return response;
    }
}
