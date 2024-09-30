package bob.command;

import bob.util.DateTime;
import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.MissingArgumentException;
import bob.task.Deadline;
import bob.task.Task;

import java.util.Map;

/**
 * Represents a command to create a deadline. The expected format is:
 * <pre>deadline &lt;desc&gt; /by &lt;date&gt;</pre>
 */
public class DeadlineCommand extends Command {
    /**
     * The string that this command corresponds to.
     * This field is collected by the parser to determine which command to execute.
     */
    public static final String COMMAND = "deadline";

    public DeadlineCommand(Map<String, String> arguments) {
        super(arguments);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String by = this.arguments.get("by");
        if (by == null) {
            throw new MissingArgumentException("'by' argument to add a deadline");
        }

        String desc = this.arguments.get("");
        if (desc == null || desc.isBlank()) {
            throw new MissingArgumentException("description of the deadline");
        }

        Task task = new Deadline(desc, DateTime.parse(by));
        tasks.add(task);
        ui.printWithFormat("added: " + task);
    }
}
