package bob.command;

import bob.util.DateTime;
import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.MissingArgumentException;
import bob.task.Event;
import bob.task.Task;

import java.util.Map;

/**
 * Represents a command to create an event. The expected format is:
 * <pre>event &lt;desc&gt; /from &lt;date&gt; /to &lt;date&gt;</pre>
 */
public class EventCommand extends Command {
    /**
     * The string that this command corresponds to.
     * This field is collected by the parser to determine which command to execute.
     */
    public static final String COMMAND = "event";

    public EventCommand(Map<String, String> arguments) {
        super(arguments);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String from = arguments.get("from");
        String to = arguments.get("to");
        String desc = arguments.get("");

        if (desc == null || desc.isBlank()) {
            throw new MissingArgumentException("description of the event");
        }
        if (from == null || to == null) {
            throw new MissingArgumentException("'from' and 'to' arguments to add an event");
        }

        Task task = new Event(desc, DateTime.parse(from), DateTime.parse(to));
        tasks.add(task);
        ui.printWithFormat("added: " + task);
    }
}
