package commands;

import tasks.TaskManager;
import exceptions.BottyException;
import tasks.Task;

public class DeleteCommand implements Command {
    @Override
    public String execute(TaskManager taskManager, ParsedInput parsedInput) throws BottyException {
        try {
            String argument = parsedInput.getArgument("main");
            int taskIndex = Integer.parseInt(argument) - 1;

            Task task = taskManager.deleteTask(taskIndex);
            return "Got it! I have removed the following task:\n" +
                    task + "\n" +
                    "You have " + taskManager.size() + " tasks left!";
        } catch (NumberFormatException ex) {
            throw new BottyException("I don't quite know what you want me to do. " +
                    "Do indicate which task to delete with its number!");
        }
    }
}
