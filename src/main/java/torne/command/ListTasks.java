package torne.command;

import torne.exception.TorneException;

import java.util.Map;

/**
 * Shows the lists of tasks to the user.
 */
public class ListTasks extends Command {
    protected ListTasks() {
        super(
                "list",
                "Shows the lists of tasks"
        );
    }

    @Override
    public void execute(Map<String, String> arguments) throws TorneException {
        String message;
        if (taskHandler.getTaskCount() == 0) {
            message = "You currently have no tasks!\nSo quiet...";
        } else {
            message = String.format("You currently have %d tasks:\n", taskHandler.getTaskCount())
                    + taskHandler.getTaskListString();
        }

        output.writeText(message);
    }
}
