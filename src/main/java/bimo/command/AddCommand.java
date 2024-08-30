package bimo.command;

import bimo.Storage;
import bimo.tasks.Task;
import bimo.TaskList;
import bimo.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("    Got it. I've added this task:");
        tasks.addTask(task);
        System.out.println("        " + task.toString());
        String word = tasks.getLength() == 1 ? "task" : "tasks";
        System.out.println(String.format("    Now you have %d %s in the tasks.", tasks.getLength(), word));
        storage.appendToFile(task);
    }
}
