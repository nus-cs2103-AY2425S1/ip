package bottleopener.command;

import bottleopener.task.Tasklist;
import bottleopener.ui.Ui;

/**
 * Represents a command to remind the user of upcoming deadlines.
 *
 * <p>This command checks the task list for tasks with deadlines and generates a reminder
 * message. If there are pending deadlines, it provides a list of them. If there are no
 * deadlines, it informs the user that there are none.
 */
public class RemindCommand implements Command {
    private Tasklist tasklist;

    /**
     * Constructs a {@code RemindCommand} object with the given task list.
     *
     * @param tasklist The {@code Tasklist} object containing the current list of tasks
     *                 to check for deadlines.
     */
    public RemindCommand(Tasklist tasklist) {
        this.tasklist = tasklist;
    }

    /**
     * Executes the reminder command by checking for tasks with deadlines and returning
     * a formatted message.
     *
     * <p>If there are tasks with deadlines, a reminder message listing the deadlines
     * is returned. If there are no deadlines, a message stating that there are no
     * deadlines is returned instead.
     *
     * @return A string containing either the list of upcoming deadlines or a message
     *         stating that there are no deadlines.
     */
    @Override
    public String runCommand() {
        if (this.tasklist.checkDeadlines()) {
            return Ui.wrapSpacer("Here is a reminder of your deadlines:\n"
                    + tasklist.showDeadlines());
        } else {
            return Ui.wrapSpacer("You have no deadlines!\n");
        }
    }
}
