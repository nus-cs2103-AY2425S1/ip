package chatbot.impl;

import chatbot.Task;
import chatbot.TaskStorage;

import java.util.ArrayList;

public class TaskStorageImpl implements TaskStorage {

    private final ArrayList<Task> tasks;

    public TaskStorageImpl() {
        tasks = new ArrayList<>();
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public Task getTask(int taskIdx) {
        return tasks.get(taskIdx);
    }

    @Override
    public int getSize() {
        return tasks.size();
    }

    @Override
    public void setTaskAsDone(int taskIdx) {
        tasks.get(taskIdx).setDone(true);
    }

    @Override
    public void setTaskAsNotDone(int taskIdx) {
        tasks.get(taskIdx).setDone(false);
    }

    @Override
    public void deleteTask(int taskIdx) {
        tasks.remove(taskIdx);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int index = 1;
        for (Task task : tasks) {
            stringBuilder.append(index).append(". ").append(task.toString()).append("\n");
            index++;
        }
        return stringBuilder.toString().trim();
    }

}
