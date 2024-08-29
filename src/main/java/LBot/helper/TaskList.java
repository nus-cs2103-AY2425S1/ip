package LBot.helper;

import LBot.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void markTask(int id) {
        taskList.get(id - 1).mark();
    }

    public Task deleteTask(int id) {
        Task task = taskList.get(id - 1);
        taskList.remove(id - 1);
        return task;
    }

    public int getLength() {
        return taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public Task getTask(int id) {
        return taskList.get(id - 1);
    }

    @Override
    public String toString() {
        if (taskList.isEmpty()) {
            return "No tasks found.";
        }
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            sb.append(count).append(". ");
            sb.append(task.toString());
            sb.append("\n");
            count++;
        }
        return sb.toString().trim();
    }
}
