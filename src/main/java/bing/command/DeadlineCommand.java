package bing.command;

import bing.task.Deadline;
import java.time.LocalDateTime;

/**
 * Represents a command to add a Deadline task.
 */
public class DeadlineCommand extends AddCommand {

    /**
     * Constructs a DeadlineCommand with the given task information and deadline.
     *
     * @param info the task description
     * @param deadline the deadline for the task
     */
    public DeadlineCommand(String info, LocalDateTime deadline) {
        super(new Deadline(info, deadline));
    }

}
