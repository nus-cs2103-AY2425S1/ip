package lutodo.commands;

import lutodo.tasklist.TaskList;
import lutodo.storage.Storage;

public class MarkCommand extends Command{
    private boolean isDone;
    private int index;

    public MarkCommand(int n, boolean isDone) {
        this.isDone = isDone;
        this.index = n;
    }
    @Override
    public void execute(TaskList tasks, Storage storage) {
        if (isDone) {
            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n"
                    + tasks.get(index));
        } else {
            tasks.get(index).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:\n"
                    + tasks.get(index));
        }
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "MarkCommand: " + isDone;
    }
}
