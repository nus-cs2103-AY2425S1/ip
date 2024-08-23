package commands;

import botty.ParsedInput;
import botty.TaskManager;
import exceptions.BottyException;
import tasks.Task;

public class DeleteCommand implements Command {
    private TaskManager taskManager;
    public DeleteCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }
    @Override
    public String execute(ParsedInput parsedInput) throws BottyException {
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
