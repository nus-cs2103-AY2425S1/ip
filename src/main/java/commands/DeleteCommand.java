package commands;

import exceptions.CenaInvalidTaskIndexException;
import storage.Storage;
import tasks.Task;

import java.util.ArrayList;

public class DeleteCommand implements Command {
    private ArrayList<Task> tasks;
    private int taskIndex;

    public DeleteCommand(ArrayList<Task> tasks, int taskIndex) {
        this.tasks = tasks;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() throws CenaInvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new CenaInvalidTaskIndexException("The task index is invalid.");
        }
        Task removedTask = tasks.remove(taskIndex);
        Storage.saveTasks(tasks);
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + removedTask);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");

    }
}
