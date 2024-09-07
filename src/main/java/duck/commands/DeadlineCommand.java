package duck.commands;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.data.task.Deadline;
import duck.storage.Storage;
import duck.ui.Ui;
import duck.util.Utils;

/**
 * Represents a command to add a Deadline task to the task list.
 * When executed, this command parses the deadline description and deadline date-time from
 * the command message and adds a new Deadline task to the task list.
 */
public class DeadlineCommand extends Command {

    /**
     * Constructs a DeadlineCommand with the specified message.
     *
     * @param message The message containing the command and its arguments.
     */
    public DeadlineCommand(String message) {
        super(message);
    }

    /**
     * Executes the command by parsing the deadline from the message and adding it to the
     * task list.
     *
     * @param tasks The list of tasks to which the new deadline will be added.
     * @param storage The storage system for saving and loading tasks.
     * @param ui The user interface for displaying messages to the user.
     * @throws DuckException If there is an error while parsing the deadline or adding it to the task list.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        super.execute(tasks, storage, ui);

        Deadline deadline = parseDeadline(message);
        tasks.addTask(deadline, storage);
    }

    /**
     * Determines whether the command signifies an exit operation.
     *
     * @return false, as this command does not trigger an exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Parses a deadline instruction from the input message and creates a Deadline object.
     * The expected format is "deadline {description} /by {deadline}", where {deadline}
     * should be in the format yyyy-MM-dd HHmm or yyyy/MM/dd HHmm.
     *
     * @param input The input message containing the deadline instruction.
     * @return A Deadline object representing the parsed deadline.
     * @throws DuckException If the input message does not match the expected format or if
     *         the deadline date-time is invalid.
     */
    private Deadline parseDeadline(String input) throws DuckException {
        // Regular expression to match the pattern for deadline
        Pattern pattern = Pattern.compile("(?i)^deadline\\s+(.+)\\s+/by\\s+(.+)$");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String description = matcher.group(1);
            String deadlineStr = matcher.group(2);
            LocalDateTime deadline = Utils.convertToDateTime(deadlineStr);
            return new Deadline(description, deadline);
        } else {
            throw new DuckException("Hey, a deadline instruction should be of the following format:\n"
                    + "deadline {description} /by {deadline}\n"
                    + "{deadline} should be in the format yyyy-MM-dd HHmm OR yyyy/MM/dd HHmm\n");
        }
    }
}
