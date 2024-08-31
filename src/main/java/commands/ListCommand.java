package commands;

import exceptions.TaskListEmptyException;

import tasks.TaskManager;

public class ListCommand extends Command {
    @Override
    public String execute(TaskManager taskManager, ParsedInput parsedInput) throws TaskListEmptyException {
        return "Here you go!\n" + taskManager.list();
    }
}
