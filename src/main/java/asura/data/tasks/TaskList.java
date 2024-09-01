package asura.data.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task removeAt(int index) {
        return taskList.remove(index);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void mark(int index) {
        taskList.get(index).markAsDone();
    }

    public void unMark(int index) {
        taskList.get(index).markAsNotDone();
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            sb.append(i + 1).append(".").append(taskList.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}
