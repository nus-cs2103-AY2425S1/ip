package kafka;

import java.util.ArrayList;

public class TaskList {

    public final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void printList() {
        for (int i = 0; i < this.tasks.size(); i++) {
            Task t = this.tasks.get(i);
            String listMessage = "  " + (i + 1) + "." + t;
            System.out.println(listMessage);
        }
    }

    public void mark(Task t) {
        t.markAsDone();
    }

    public void unmark(Task t) {
        t.markAsNotDone();
    }

    public void delete(int taskNumber) {
        if (this.tasks.isEmpty()) {
            return;
        }
        this.tasks.remove(taskNumber - 1);
    }

    public int getSize() {
        return this.tasks.size();
    }
}
