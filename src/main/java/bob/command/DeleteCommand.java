package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.IncorrectArgumentException;
import bob.exception.MissingArgumentException;
import bob.task.Task;

import java.util.Map;

/**
 * Represents a command to delete a task. The expected format is: delete &lt;index&gt;
 */
public class DeleteCommand extends Command {
    /**
     * The string that this command corresponds to.
     * This field is collected by the parser to determine which command to execute.
     */
    public static final String COMMAND = "delete";

    public DeleteCommand(Map<String, String> arguments) {
        super(arguments);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String argument = this.arguments.get("");
        if (argument == null || argument.isBlank()) {
            throw new MissingArgumentException("index of the task that you want to delete");
        }

        int index;
        try {
            index = Integer.parseInt(argument) - 1;
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException("an integer");
        }
        if (index < 0 || index >= tasks.size()) {
            throw new IncorrectArgumentException("a valid index");
        }

        Task task = tasks.remove(index);
        ui.printWithFormat("OK, I've removed this task:\n"
                + task + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
    }
}
