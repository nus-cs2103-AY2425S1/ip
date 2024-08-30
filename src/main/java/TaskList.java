package main.java;

import java.sql.Array;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> list) {
        taskList.addAll(list);
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public void removeTask(int index) {
        taskList.remove(index - 1);
    }

    public String displayTask() {
        StringBuilder sb = new StringBuilder();
        taskList.forEach((task) -> {
            sb.append(taskList.indexOf(task) + 1).append(". ").append(task).append("\n");
        });
        return sb.toString();
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }
}
