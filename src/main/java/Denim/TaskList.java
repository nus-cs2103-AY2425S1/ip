package denim;

import denim.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>(100);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void addTaskAtIndex(int index, Task task) {
        taskList.add(index, task);
    }

    public void deleteTaskAtIndex(int index) {
        taskList.remove(index);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void markTask(int index) {
        this.taskList.get(index).setIsDone(true);
    }

    public void unmarkTask(int index) {
        taskList.get(index).setIsDone(false);
    }

    public boolean isValidIndex(int trueIndex) {
        return trueIndex >= 0 && trueIndex < getTaskListSize();
    }

    public int getTaskListSize() {
        return taskList.size();
    }

    public String printList() {
        if (taskList.isEmpty()) {
            return "Your Task List is Empty.\n";
        }

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            stringList.add(String.format("%d %s", i + 1, this.getTask(i)));
        }

        String returnListMessage = String.join("\n", stringList);
        return returnListMessage;
    }
}
