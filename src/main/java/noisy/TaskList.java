package noisy;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addToList(Task task) {
        this.tasks.add(task);
    }

    public void deleteFromList(int i) {
        this.tasks.remove(i);
    }

    public int getListSize() {
        return this.tasks.size();
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    public void markDoneFromList(int i) {
        this.tasks.get(i).markDone();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

}
