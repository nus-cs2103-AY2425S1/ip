package Johnson.command;

import Johnson.task.Deadline;
import Johnson.utils.Utilities;

/**
 * Represents a command to add a Deadline task.
 */
public class DeadlineCommand extends Command {
    /**
     * The command word that identifies a DeadlineCommand instance.
     */
    public static final String COMMAND_WORD = "deadline";

    /**
     * The message that is displayed when a DeadlineCommand instance is executed successfully.
     */
    private static final String COMMAND_MSG = "Let's move, Chief! Got another deadline to hit:\n";
    private final Deadline deadline;

    /**
     * Constructs a DeadlineCommand with the specified task, date, time and tags.
     *
     * @param task the name of the task.
     * @param date the date of the task.
     * @param time the time of the task.
     * @param tags the tags of the task.
     */
    public DeadlineCommand(String task, String date, String time, String... tags) {
        this.deadline = new Deadline(task, date, time, tags);
    }

    /**
     * Constructs a DeadlineCommand with the specified task, date and tags.
     *
     * @param task the name of the task.
     * @param date the date of the task.
     * @param tags the tags of the task.
     */
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
