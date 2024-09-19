package torne.command;

import torne.command.argument.CommandArgument;
import torne.command.argument.CommandArgumentList;
import torne.exception.TorneException;
import torne.task.Task;

import java.util.List;
import java.util.Map;

/**
 * Deletes the task at the specified index.
 */
public class DeleteTask extends Command {
    protected DeleteTask() {
        super(
                "delete",
                "Deletes a task with the given index",
                new CommandArgumentList(List.of(
                        new CommandArgument("", "Index", "Index of task to be deleted")
                ))
        );
    }

    @Override
    public void execute(Map<String, String> arguments) throws TorneException {
        String indexStr = arguments.get("");

        if (indexStr == null) {
            output.error("No task index specified.");
            return;
        }

        try {
            int index = Integer.parseInt(indexStr.trim()) - 1;

            if (index < 0 || index >= taskHandler.getTaskCount()) {
                // TODO I'm guessing task handler should be the one raising this exception! SAME AS THE OTHER TWO
                output.error("Invalid task index. Out of range.");
                return;
            }

            Task removed = taskHandler.removeTask(index);
            int count = taskHandler.getTaskCount();

            String message = "Alright, I'll delete this task:\n" + removed
                    + String.format("\nNow you have %d task", count)
                    + ((count > 1) ? "s left!" : " left!");
            output.writeText(message);
        } catch (NumberFormatException e) {
            output.error("Invalid task index. It is not an integer.");
        }
    }
}
