package command;

import tasklist.TaskList;
import tasks.Task;
import tasks.ToDo;
import ui.CommandLineUi;

/**
 * Represents a command to add a to-do task to the task list.
 */
public class AddToDoCommand extends Command {
    /** The task to be added to the task list. */
    protected Task task;

    /**
     * Constructs an AddToDoCommand with the specified description.
     *
     * @param desc The description of the to-do task.
     */
    public AddToDoCommand(String desc) {
        task = new ToDo(desc);
    }

    /**
     * Executes the command by adding the to-do task to the task list and
     * displaying the appropriate messages in the command line interface.
     *
     * @param tasklist The TaskList where the task will be added.
     * @param ui       The CommandLineUI used to interact with the user.
     */
    public void execute(TaskList tasklist, CommandLineUi ui) {
        tasklist.addTask(task);

        ui.speakLine("Got it. I've added this task:");
        ui.speakLine("  " + task);
        ui.speakLine("Now you have " + tasklist.size() + " tasks in the list.");
    }

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return false, as this command does not cause the application to exit.
     */
    public boolean isExit() {
        return false;
    }
}
