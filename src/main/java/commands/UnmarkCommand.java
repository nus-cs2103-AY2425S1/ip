package commands;

import botty.ParsedInput;
import botty.TaskManager;
import exceptions.BottyException;
import tasks.Task;

public class UnmarkCommand implements Command {
    private TaskManager taskManager;
    public UnmarkCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }
    @Override
    public String execute(ParsedInput parsedInput) throws BottyException {
        try {
            String argument = parsedInput.getArgument("main");

            int taskIndex = Integer.parseInt(argument) - 1;
            Task task = taskManager.unmarkTask(taskIndex);

            return "It's okay, we can get that done later. I'll mark that as undone for you.\n" + task;
        } catch (NumberFormatException ex) {
            throw new BottyException("I don't quite know what you want me to do. " +
                    "Do indicate which task to unmark with its number!");
        }
    }
}
