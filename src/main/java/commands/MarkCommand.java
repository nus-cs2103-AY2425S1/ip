package commands;

import exceptions.BottyException;
import tasks.Task;
import tasks.TaskManager;

public class MarkCommand extends Command {
    @Override
    public String execute(TaskManager taskManager, ParsedInput parsedInput) throws BottyException {
        try {
            String argument = parsedInput.getArgument("main");

            int taskIndex = Integer.parseInt(argument) - 1;
            Task task = taskManager.markTask(taskIndex);

            return "Congrats on completing that! Let me just mark that as done for you.\n" + task;
        } catch (NumberFormatException ex) {
            throw new BottyException("I don't quite know what you want me to do. " +
                    "Do indicate which task to mark with its number!");
        }
    }
}
