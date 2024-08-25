package rob;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.taskList = tasks;
    }

    public int len() {
        return taskList.size();
    }

    public Task getTask(int index) {
        if (index >= 0 && index < taskList.size()) {
            return taskList.get(index);
        } else {
            System.out.println("Invalid index: " + index);
            return null;
        }
    }

    public List<Task> getTasks() {
        return taskList;
    }

    public void removeTask(int i) {
        taskList.remove(i);
    }

}
