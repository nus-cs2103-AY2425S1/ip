package shnoop.tasks;


import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public boolean mark(int idx) {
        return tasks.get(idx).markTask();
    }

    public boolean unmark(int idx) {
        return tasks.get(idx).unmarkTask();
    }

    public String list() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) {
                break;
            }
            result += ((i + 1) + ". " + tasks.get(i).toString());
            result += "\n";
        }
        return result;
    }

    public Task delete(int idx) {
        Task task = tasks.get(idx);
        tasks.remove(idx);
        return task;
    }
}
