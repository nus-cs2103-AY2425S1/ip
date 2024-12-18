package barry.command;

import barry.task.Deadline;

/**
 * Represents a command that adds a deadline task to the task list.
 */
public class DeadlineCommand extends AddCommand {

    /**
     * Constructs a DeadlineCommand with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date by which the task should be completed, in yyyy-mm-dd format.
     */
    public DeadlineCommand(String description, String by) {
        super(new Deadline(description, by));
    }
}
