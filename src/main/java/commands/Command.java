package commands;

import exceptions.BottyException;
import tasks.TaskManager;

public abstract class Command {
    public abstract String execute(TaskManager taskManager, ParsedInput parsedInput) throws BottyException;

    public boolean isExit() {
        return false;
    }
}
