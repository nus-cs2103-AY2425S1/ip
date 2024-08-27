package command;

import tasklist.TaskList;
import tasklist.TaskListOutOfBoundsException;
import ui.CommandLineUI;

public class MarkCommand extends Command {
    protected int index;

    public MarkCommand(int index) {
        this.index = index;

    }

    public void execute(TaskList tasklist, CommandLineUI ui) {
        try {
            tasklist.mark(index);
    
            ui.speakLine("Nice! I've marked this task as done: ");
            ui.speakLine("  " + tasklist.getTask(index));

        } catch (TaskListOutOfBoundsException e) {
            ui.speakLine(e.getMessage());
        }

    }

    public boolean isExit() {
        return false;
    }
}
