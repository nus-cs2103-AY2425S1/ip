package lutodo.commands;

import lutodo.tasklist.TaskList;
import lutodo.storage.Storage;

public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            System.out.println("Noted. I've removed this task:\n" + tasks.get(index) +
                    "\nNow you have " + (tasks.size() - 1) + " tasks in the list.");
            tasks.deleteTask(index);
        } catch(IndexOutOfBoundsException e) {
            System.out.println("The task you want to delete is not in task list, please try again.");
        }
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "DeleteCommand: " + index;
    }
}
