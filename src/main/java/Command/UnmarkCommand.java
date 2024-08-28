package Command;

import Task.TaskList;
import Task.InvalidTaskException;

import Ui.Ui;

public class UnmarkCommand extends Command {

    private TaskList tasks;
    private int index;

    public UnmarkCommand(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public void execute() {
        try {
            tasks.unmark(index);
            Ui.printText("Task has been unmarked.\n" + tasks.get(index).toString());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException("ERROR! Task not found.");
        }
    }

    @Override
    public boolean isTerminated() {
        return false;
    }
}
