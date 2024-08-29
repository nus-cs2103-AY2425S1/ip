package poChat.bot;

import poChat.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> listTasks;

    public TaskList() {
        this.listTasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> listTasks) {
        this.listTasks = listTasks;
    }

    public void add(Task task) {
        this.listTasks.add(task);
    }

    public int size() {
        return this.listTasks.size();
    }

    public void remove(Task task) {
        this.listTasks.remove(task);
    }

    public Task get(int index) {
        return this.listTasks.get(index);
    }

    public void printMatchingTasks(String keyword) {
        System.out.println("Here are the matching tasks in your list:");

        int index = 1;
        for (Task task : this.listTasks) {
            if (task.contains(keyword)) {
                System.out.println(index + ". " + task);
                index++;
            }
        }
    }

    public ArrayList<Task> toList() {
        return this.listTasks;
    }
}
