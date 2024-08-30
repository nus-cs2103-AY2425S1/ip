package pebble;

import java.util.ArrayList;

public class TasksList {
    private ArrayList<Task> tasks;

    public TasksList() {
        this.tasks = new ArrayList<>();
    }

    public TasksList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public TasksList getFilteredList(String s) {
        ArrayList<Task> temp = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            temp.add(this.getTask(i));
        }
        temp.removeIf(task -> !task.description.contains(s));
        return new TasksList(temp);
    }
}
