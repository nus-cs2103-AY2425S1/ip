package Johnson.command;

import Johnson.task.Deadline;
import Johnson.utils.Utilities;

/**
 * Represents a command to add a Deadline task.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private static final String COMMAND_MSG = "Let's move, Chief! Got another deadline to hit:\n";
    private final Deadline deadline;


    public DeadlineCommand(String task, String date, String time, String... tags) {
        this.deadline = new Deadline(task, date, time, tags);
    }

    public DeadlineCommand(String task, String date, String... tags) {
        this.deadline = new Deadline(task, date, tags);
    }

    public Deadline getDeadline() {
        return this.deadline;
    }

    @Override
    public String executeCommand() {
        Utilities.OutlineMessage(COMMAND_MSG + deadline);
        Command.taskList.addTask(deadline);
        return (COMMAND_MSG + deadline);
    }
}
