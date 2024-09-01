package mryapper.task;

import mryapper.storagemanager.StorageManager;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public Task remove(int taskNumber) {
        return taskList.remove(taskNumber - 1);
    }

    public int count() {
        return this.taskList.size();
    }

    public Task mark(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.markAsDone();
        return task;
    }

    public Task unmark(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.markAsNotDone();
        return task;
    }

    public void saveToStorage(StorageManager storage) {
        try {
            storage.saveTasks(this.taskList);
        } catch (IOException e) {
            System.out.println(" Something went wrong when saving your changes to the task list :(");
        }
    }

    @Override
    public String toString() {
        int listSize = this.count();
        if (listSize == 0) {
            return "You do not have any tasks!";
        }

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
