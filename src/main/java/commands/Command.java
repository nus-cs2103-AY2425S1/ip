package commands;

import exceptions.BottyException;
import tasks.TaskManager;

public interface Command {
    String execute(TaskManager taskManager, ParsedInput parsedInput) throws BottyException;
}
