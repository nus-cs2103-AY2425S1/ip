package command;

import tasklist.TaskList;
import tasklist.TaskListOutOfBoundsException;
import ui.CommandLineUI;

public class UnmarkCommand extends Command {
    protected int index;

    public UnmarkCommand(int index) {
        this.index = index;

    }

    public void execute(TaskList tasklist, CommandLineUI ui) {

        try {
            tasklist.unmark(index);

            ui.speakLine("OK, I've marked this task as not done yet: ");
            ui.speakLine("  " + tasklist.getTask(index));

        } catch (TaskListOutOfBoundsException e) {
            ui.speakLine(e.getMessage());
        }

    }

    public boolean isExit() {
        return false;
    }
}
