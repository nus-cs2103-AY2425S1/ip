package botty.commands;

import botty.exceptions.BottyException;
import botty.tasks.TaskManager;

public abstract class Command {
    public abstract String execute(TaskManager taskManager, ParsedInput parsedInput) throws BottyException;

    public boolean isExit() {
        return false;
    }
}
