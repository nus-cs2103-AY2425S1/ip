package johncena.commands;

import java.util.ArrayList;

import johncena.exceptions.CenaInvalidTaskIndexException;
import johncena.storage.Storage;
import johncena.tasks.Task;

public class UnmarkCommand implements Command{
    private ArrayList<Task> tasks;
    private int taskIndex;

    public UnmarkCommand(ArrayList<Task> tasks, int taskIndex) {
        this.tasks = tasks;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() throws CenaInvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new CenaInvalidTaskIndexException("The task index is invalid.");
        }
        tasks.get(taskIndex).markAsNotDone();
        Storage.saveTasks(tasks);
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + tasks.get(taskIndex));
        System.out.println("____________________________________________________________");
    }

}
