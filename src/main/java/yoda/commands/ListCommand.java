package yoda.commands;

import yoda.TaskList;
import yoda.tasks.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    protected ArrayList<Task> tasks;

    public ListCommand(TaskList tasks) {
        this.tasks = tasks.getTasks();
    }

    public void run() {
        System.out.println("Do or do not, there is no try.");
        Task[] taskArray = tasks.toArray(new Task[0]);
        for (int i = 0; i < taskArray.length; i++) {
            System.out.printf("%d. %s\n", i + 1, taskArray[i]);
        }
    }
}
