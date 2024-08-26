package hoshi.task;

import hoshi.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskList with an initial list of tasks.
     *
     * @param tasks the initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void delete(int index) {

        taskList.remove(index);
    }

    public void setMark(int index) {
        taskList.get(index).setIsDone(true);
    }

    public int size() {
        return taskList.size();
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            result.append(i + 1).append(". ").append(taskList.get(i).toString()).append("\n");
        }

        return result.toString();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }
}
