package tasks;

import static utility.Printer.printWithDivider;

import java.util.ArrayList;

import storage.Storage;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    public TaskList(Storage storage) {
        this.tasks = storage.load();
        this.storage = storage;
    }

    public int size() {
        return this.tasks.size();
    }

    public void listTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            sb.append(String.format("%d.%s\n", i + 1, task));
        }
        printWithDivider(sb.toString());
    }

    public void mark(int index) {
        Task task = this.tasks.get(index);
        task.setDone(true);
        this.storage.save(this.tasks);
        printWithDivider(String.format("Nice! I've marked this task as done:\n  %s\n", task));
    }

    public void unmark(int index) {
        Task task = this.tasks.get(index);
        task.setDone(false);
        this.storage.save(this.tasks);
        printWithDivider(String.format("OK, I've marked this task as not done yet:\n  %s\n", task));
    }

    public void add(Task task) {
        this.tasks.add(task);
        this.storage.save(this.tasks);
        printWithDivider(String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n", task, this.size()
        ));
    }

    public void remove(int index) {
        Task task = this.tasks.remove(index);
        this.storage.save(this.tasks);
        printWithDivider(String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.\n", task, this.size())
        );
    }
}
