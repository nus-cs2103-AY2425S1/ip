package commands;

import tasks.TaskManager;
import exceptions.TaskListEmptyException;

public class ListCommand implements Command {
    private final TaskManager taskManager;
    public ListCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }
    @Override
    public String execute(ParsedInput parsedInput) throws TaskListEmptyException {
        return "Here you go!\n" + taskManager.list();
    }
}
