package torne.command;

import torne.command.argument.CommandArgument;
import torne.command.argument.CommandArgumentList;
import torne.exception.TorneException;

import java.util.List;
import java.util.Map;

public class UnmarkTask extends Command {
    protected UnmarkTask() {
        super(
                "mark",
                "Marks a particular task as incomplete",
                new CommandArgumentList(List.of(
                        new CommandArgument("", "Index", "Index of task to be unmarked")
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
                // TODO I'm guessing task handler should be the one raising this exception!
                output.error("Invalid task index. Out of range");
                return;
            }

            if (!taskHandler.getTask(index).getIsDone()) {
                output.writeText("Excuse me, this task is already not done. I can't make it even less done.");
            } else {
                taskHandler.getTask(index).markAsNotDone();
                output.writeText("Unmarking this task :(\n    " + taskHandler.getTask(index));
            }

        } catch (NumberFormatException e) {
            output.error("Invalid task index. It is not an integer.");
        }
    }
}
