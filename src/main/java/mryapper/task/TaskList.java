package mryapper.task;

import mryapper.storagemanager.StorageManager;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private final StorageManager storageSource;
    private final ArrayList<Task> taskList;

    public TaskList(StorageManager storage) {
        this.taskList = new ArrayList<>(100);
        this.storageSource = storage;
    }

    public TaskList(StorageManager storage, ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.storageSource = storage;
    }

    public void add(Task task) {
        this.taskList.add(task);
        this.saveToStorage();
    }

    public Task remove(int taskNumber) {
        Task taskRemoved = taskList.remove(taskNumber - 1);
        this.saveToStorage();
        return taskRemoved;
    }

    public int count() {
        return this.taskList.size();
    }

    public Task mark(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.markAsDone();
        this.saveToStorage();
        return task;
    }

    public Task unmark(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.markAsNotDone();
        this.saveToStorage();
        return task;
    }

    public void saveToStorage() {
        try {
            this.storageSource.saveTasks(this.taskList);
        } catch (IOException e) {
            System.out.println(" Something went wrong when saving your changes to the task list :(");
        }
    }

    @Override
    public String toString() {
        int listSize = this.count();
        String listInString = "";
        for (int i = 0; i < listSize; i += 1) {
            String taskString = String.format(" %d.%s", i + 1, taskList.get(i));
            listInString += taskString;
            if (i < listSize - 1) {
                listInString += "\n";
            }
        }
        return listInString;
    }
}
