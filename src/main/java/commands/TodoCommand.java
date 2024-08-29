package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDoTask;
import ui.Ui;

import java.util.ArrayList;

public class TodoCommand extends Command {
    private ToDoTask task;
    public TodoCommand(ToDoTask task) {
        this.task = task;
    }
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> list = tasks.getTasks();
        list.add(this.task);
        System.out.println("\nEl Primo:");
        System.out.println("Got it. I've added this task:");
        System.out.println(this.task);
        System.out.printf("Now you have %d tasks in the list.%n", list.size());
        storage.updateStorage(tasks);
    }
}
