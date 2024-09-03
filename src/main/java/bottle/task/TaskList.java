package bottle.task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }
    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task removeTask(int index) {
        if (index >= 0 && index < taskList.size()) {
            return taskList.remove(index);
        } else {
            throw new IndexOutOfBoundsException("OOPS!!! Task number is out of bounds.");
        }
    }

    public String toString() {
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            int idx = i + 1;
            tasks.append(idx).append(". ").append(taskList.get(i)).append("\n");
        }
        return tasks.toString();
    }
}
