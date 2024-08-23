package commands;

import botty.ParsedInput;
import botty.TaskManager;
import exceptions.TaskListEmptyException;

public class ListCommand implements Command {
    private TaskManager taskManager;
    public ListCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }
    @Override
    public String execute(ParsedInput parsedInput) throws TaskListEmptyException {
        return "Here you go!\n" + taskManager.list();
    }
}
