package botty.commands;

import botty.exceptions.BottyException;
import botty.tasks.Task;
import botty.tasks.TaskManager;

public class UnmarkCommand extends Command {
    @Override
    public String execute(TaskManager taskManager, ParsedInput parsedInput) throws BottyException {
        try {
            String argument = parsedInput.getArgument("main");

            int taskIndex = Integer.parseInt(argument) - 1;
            Task task = taskManager.unmarkTask(taskIndex);

            return "It's okay, we can get that done later. I'll mark that as undone for you.\n" + task;
        } catch (NumberFormatException ex) {
            throw new BottyException("I don't quite know what you want me to do. "
                    + "Do indicate which task to unmark with its number!");
        }
    }
    @Override
    public boolean changesTaskList() {
        return true;
    }
}
