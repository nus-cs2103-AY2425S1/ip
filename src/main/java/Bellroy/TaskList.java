package Bellroy;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public List<Task> findTask(String keyword) {
        List<Task> matchingTask = new ArrayList<>();
        for (Task task: taskList) {
            if (task.description.contains(keyword)) {
                matchingTask.add(task);
            }
        }
        return matchingTask;
    }

}
