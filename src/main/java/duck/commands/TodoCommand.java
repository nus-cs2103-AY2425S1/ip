package duck.commands;

import java.util.Map;

import duck.Parser;
import duck.TaskList;
import duck.exceptions.MissingArgsException;
import duck.exceptions.TodoUsageException;
import duck.tasks.Task;
import duck.tasks.Todo;

public class TodoCommand extends TaskCommand {
    private static final Map<String, String> ARGS = Map.ofEntries();

    public TodoCommand(TaskList taskList, Parser lineBuffer) {
        super(taskList, lineBuffer, ARGS);
    }

    @Override
    public String createNewTask(TaskList taskList) throws MissingArgsException {
        verifyArgsArePresent(new TodoUsageException());
        String description = parts.get("");
        Task task = new Todo(description);
        String response = handleNewTask(task);
        return response;
    }
}