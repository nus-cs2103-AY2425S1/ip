package Kita;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    public void addTask(Task taskObj) {
        this.tasks.add(taskObj);
    }

    public Task getTask(Integer index) {
        return this.tasks.get(index);
    }

    public void removeTask(int index) {
        Task removed = this.tasks.remove(index);
        System.out.println("  " + removed);
    }

    public int size() {
        return this.tasks.size();
    }

    public void setTaskCompleted(Integer index, boolean status) {
        Task theTask = this.tasks.get(index);
        theTask.setCompleted(status);
        System.out.println("  " + theTask);
    }

    @Override
    public String toString() {
        StringBuilder finalStr = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            finalStr.append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        return finalStr.toString();
    }
}
