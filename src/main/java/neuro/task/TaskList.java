package neuro.task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;
    public TaskList(Object o) {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public String getSize() {
        return String.valueOf(taskList.size());
    }
}
