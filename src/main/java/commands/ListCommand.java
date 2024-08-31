package commands;

import tasks.TaskManager;
import exceptions.TaskListEmptyException;

public class ListCommand implements Command {
    @Override
    public String execute(TaskManager taskManager, ParsedInput parsedInput) throws TaskListEmptyException {
        return "Here you go!\n" + taskManager.list();
    }
}
