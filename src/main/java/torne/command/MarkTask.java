package torne.command;

import torne.command.argument.CommandArgument;
import torne.command.argument.CommandArgumentList;
import torne.exception.TorneException;

import java.util.List;
import java.util.Map;

/**
 * Marks a particular task as complete.
 */
public class MarkTask extends Command {
    protected MarkTask() {
        super(
                "mark",
                "Marks a particular task as complete",
                new CommandArgumentList(List.of(
                        new CommandArgument("", "Index", "Index of task to be marked")
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

            if (!taskHandler.indexChecker(index, output)) {
                return;
            }

            if (taskHandler.getTask(index).getIsDone()) {
                output.writeText("This task is already done, stop wasting my time.");
            } else {
                taskHandler.getTask(index).markAsDone();
                output.writeText("Marking this task as done :)\n    " + taskHandler.getTask(index));
            }

        } catch (NumberFormatException e) {
            output.error("Invalid task index. It is not an integer.");
        }
    }
}
