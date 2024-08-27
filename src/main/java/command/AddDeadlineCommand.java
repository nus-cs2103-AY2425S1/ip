package command;

import ouiouibaguette.OuiOuiBaguetteException;
import tasklist.TaskList;
import tasks.Deadline;
import tasks.Task;
import ui.CommandLineUI;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class AddDeadlineCommand extends Command {
    /** The task to be added to the task list. */
    protected Task task;

    public AddDeadlineCommand(String desc, String date) throws OuiOuiBaguetteException {
        task = new Deadline(desc, date);

    }

    public void execute(TaskList tasklist, CommandLineUI ui) {
        tasklist.addTask(task);

        ui.speakLine("Got it. I've added this task:");
        ui.speakLine("  " + task);
        ui.speakLine("Now you have " + tasklist.size() + " tasks in the list.");

    }

    public boolean isExit() {
        return false;
    }
}
