package command;

import tasklist.TaskList;
import tasklist.TaskListOutOfBoundsException;
import tasks.Task;
import ui.CommandLineUI;

public class DeleteCommand extends Command {
    protected int index;

    public DeleteCommand(int index) {
        this.index = index;

    }

    public void execute(TaskList tasklist, CommandLineUI ui) {

        try {
            Task task = tasklist.delete(index);

            ui.speakLine("Noted. I've removed this task:");
            ui.speakLine("  " + task);
            ui.speakLine("Now you have " + tasklist.size() + " tasks in the list.");

        } catch (TaskListOutOfBoundsException e) {
            ui.speakLine(e.getMessage());
        }

    }

    public boolean isExit() {
        return false;
    }
}
