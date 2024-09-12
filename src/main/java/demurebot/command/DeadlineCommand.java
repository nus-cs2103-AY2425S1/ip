package demurebot.command;

import demurebot.task.Deadline;
import demurebot.task.TaskList;
import demurebot.ui.Ui;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    private final Deadline deadline;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param deadline Deadline task to be added to the task list.
     */
    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Executes the command to add a deadline task to the task list.
     *
     * @param list Task list containing all tasks.
     * @param ui Ui to interact with the user.
     * @return The result of the command.
     */
    @Override
    public String execute(TaskList list, Ui ui) {
        list.addTask(this.deadline);
        return ui.displayAddTask(this.deadline, list.getSize());
    }
}
