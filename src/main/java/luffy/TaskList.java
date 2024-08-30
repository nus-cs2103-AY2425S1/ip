package luffy;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> existingTasks) {
        this.taskList = existingTasks;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    public int size() {
        return this.taskList.size();
    }

}
